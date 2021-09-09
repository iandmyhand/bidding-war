package com.study.biddingwar.common.crypto

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoAesUtils {
    companion object{
        fun decryptAES128Msg(encText:String, secretKey:String): String {
            val ivParameterSpec = IvParameterSpec(secretKey.toByteArray())
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
            val byteEncData = Base64.getDecoder().decode(encText.toByteArray())
            val byteDecData = cipher.doFinal(byteEncData)
            return String(byteDecData)
        }

        fun decryptAES128Msg(encText:String, secretKey:String, iv:String): String {
            val ivParameterSpec = IvParameterSpec(iv.toByteArray())
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
            val byteEncData = Base64.getDecoder().decode(encText.toByteArray())
            val byteDecData = cipher.doFinal(byteEncData)
            return String(byteDecData)
        }

        fun encryptAES128Msg(plainText:String, secretKey: String): String{
            val ivParameterSpec = IvParameterSpec(secretKey.toByteArray())
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
            val encByteArray = cipher.doFinal(plainText.toByteArray())
            return Base64.getEncoder().encodeToString(encByteArray)
        }

        fun encryptAES128Msg(plainText:String, secretKey: String, iv:String): String{
            val ivParameterSpec = IvParameterSpec(iv.toByteArray())
            val secretKeySpec = SecretKeySpec(secretKey.toByteArray(), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
            val encByteArray = cipher.doFinal(plainText.toByteArray())
            return Base64.getEncoder().encodeToString(encByteArray)
        }

    }
}