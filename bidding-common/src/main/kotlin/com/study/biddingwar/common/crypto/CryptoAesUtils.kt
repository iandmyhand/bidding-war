package com.study.biddingwar.common.crypto

import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoAesUtils {
    companion object {
        fun decryptAES128Msg(encText: String, secretKey: String): String {
            try {
                val ivParameterSpec = IvParameterSpec(secretKey.toByteArray(charset = Charsets.UTF_8))
                val secretKeySpec = SecretKeySpec(secretKey.toByteArray(charset = Charsets.UTF_8), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
                val byteEncData = Base64.getDecoder().decode(encText.toByteArray(charset = Charsets.UTF_8))
                val byteDecData = cipher.doFinal(byteEncData)
                return String(byteDecData)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun decryptAES128Msg(encText: String, secretKey: String, iv: String): String {
            try {
                val ivParameterSpec = IvParameterSpec(iv.toByteArray(charset = Charsets.UTF_8))
                val secretKeySpec = SecretKeySpec(secretKey.toByteArray(charset = Charsets.UTF_8), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
                val byteEncData = Base64.getDecoder().decode(encText.toByteArray(charset = Charsets.UTF_8))
                val byteDecData = cipher.doFinal(byteEncData)
                return String(byteDecData)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun encryptAES128Msg(plainText: String, secretKey: String): String {
            try {
                val ivParameterSpec = IvParameterSpec(secretKey.toByteArray(charset = Charsets.UTF_8))
                val secretKeySpec = SecretKeySpec(secretKey.toByteArray(charset = Charsets.UTF_8), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
                val encByteArray = cipher.doFinal(plainText.toByteArray(charset = Charsets.UTF_8))
                return Base64.getEncoder().encodeToString(encByteArray)

            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        fun encryptAES128Msg(plainText: String, secretKey: String, iv: String): String {
            try {
                val ivParameterSpec = IvParameterSpec(iv.toByteArray(charset = Charsets.UTF_8))
                val secretKeySpec = SecretKeySpec(secretKey.toByteArray(charset = Charsets.UTF_8), "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
                val encByteArray = cipher.doFinal(plainText.toByteArray(charset = Charsets.UTF_8))
                return Base64.getEncoder().encodeToString(encByteArray)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

    }
}