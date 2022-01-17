package com.study.biddingwar.common.crypto

import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class CryptoRsaUtils {
    companion object {
        fun decryptMsg(encMsg: String, privateKey: PrivateKey): String? {
            try {
                val cipher: Cipher = Cipher.getInstance("RSA")
                cipher.init(Cipher.DECRYPT_MODE, privateKey)
                val byteEncData = Base64.getDecoder().decode(encMsg.toByteArray())
                val byteDecData = cipher.doFinal(byteEncData)
                return String(byteDecData)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun encryptMsg(plainTxt: String, publicKey: PublicKey): String {
            try {
                val cipher = Cipher.getInstance("RSA")
                cipher.init(Cipher.ENCRYPT_MODE, publicKey)
                val encryptByte = cipher.doFinal(plainTxt.toByteArray())
                return Base64.getEncoder().encodeToString(encryptByte)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun getPrivateKey(base64PrivateKey: String): PrivateKey {
            val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
            val byteKeyArray = Base64.getDecoder().decode(base64PrivateKey.toByteArray())
            val privateKeySpec = PKCS8EncodedKeySpec(byteKeyArray)
            return keyFactory.generatePrivate(privateKeySpec)
        }

        fun getPublicKey(base64PublicKey: String): PublicKey {
            try {
                val x509EncodedKeySpec = X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.toByteArray()))
                val keyFactory: KeyFactory = KeyFactory.getInstance("RSA")
                return keyFactory.generatePublic(x509EncodedKeySpec)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}