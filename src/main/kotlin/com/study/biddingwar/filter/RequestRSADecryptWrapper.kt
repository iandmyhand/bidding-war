package com.study.biddingwar.filter

import com.study.biddingwar.common.crypto.CryptoRsaUtils
import java.io.*
import java.security.PrivateKey
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper


class RequestRSADecryptWrapper: HttpServletRequestWrapper{

    var body:String

    constructor(request: HttpServletRequest, privateKey: PrivateKey) : super(request) {

        val stringBuilder = StringBuilder()
        var bufferedReader: BufferedReader? = null
        try {
            val inputStream: InputStream? = request.inputStream
            if (inputStream != null) {
                bufferedReader = BufferedReader(InputStreamReader(inputStream))
                val charBuffer = CharArray(128)
                var bytesRead = -1
                while (bufferedReader.read(charBuffer).also { bytesRead = it } > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead)
                }
            } else {
                stringBuilder.append("")
            }
        } catch (ex: IOException) {
            throw ex
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close()
                } catch (ex: IOException) {
                    throw ex
                }
            }
        }
        val encryptData = stringBuilder.toString()

        var deCryptData: String? = null
        if (encryptData != null && !encryptData.isEmpty()) {
            deCryptData = CryptoRsaUtils.decryptMsg(encryptData, privateKey)
            if (deCryptData == null) {
                throw RuntimeException("data not found body")
            }
        }
        this.body = deCryptData!!

    }

    @Throws(IOException::class)
    override fun getInputStream(): ServletInputStream? {
        val byteArrayInputStream = ByteArrayInputStream(body.toByteArray())
        return object : ServletInputStream() {
            @Throws(IOException::class)
            override fun read(): Int {
                return byteArrayInputStream.read()
            }

            override fun isFinished(): Boolean {
                // TODO Auto-generated method stub
                return false
            }

            override fun isReady(): Boolean {
                // TODO Auto-generated method stub
                return false
            }

            override fun setReadListener(listener: ReadListener) {
                // TODO Auto-generated method stub
            }
        }
    }

    @Throws(IOException::class)
    override fun getReader(): BufferedReader? {
        return BufferedReader(InputStreamReader(this.inputStream))
    }

}