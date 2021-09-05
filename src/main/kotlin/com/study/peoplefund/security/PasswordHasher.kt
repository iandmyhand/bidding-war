package com.study.peoplefund.security

import org.springframework.stereotype.Component
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Component
class PasswordHasher(
        val random: SecureRandom
) {

    fun generateSalt(): ByteArray {
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    fun hash(password: String, salt: ByteArray): String {
        val spec = PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, KEY_LENGTH)

        try {
            val secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM)
            return String(secretKeyFactory.generateSecret(spec).encoded)
        } catch (e: NoSuchAlgorithmException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } catch (e: InvalidKeySpecException) {
            throw AssertionError("Error while hashing a password: " + e.message, e)
        } finally {
            spec.clearPassword()
        }
    }

    companion object {
        const val ITERATION_COUNT = 1000
        const val KEY_LENGTH = 256
        const val ALGORITHM = "PBKDF2WithHmacSHA1"
    }
}