package com.study.biddingwar.common

import com.study.biddingwar.common.crypto.CryptoRsaUtils
import com.study.biddingwar.domain.RsaKeyStore
import org.springframework.context.support.EmbeddedValueResolutionSupport
import org.springframework.format.AnnotationFormatterFactory
import org.springframework.format.Formatter
import org.springframework.format.Parser
import org.springframework.format.Printer
import java.security.PrivateKey
import java.security.PublicKey
import java.text.ParseException
import java.util.*

class DecryptAnnotationFormatterFactory : EmbeddedValueResolutionSupport(),
    AnnotationFormatterFactory<DecryptRsa> {
    override fun getFieldTypes(): Set<Class<*>> {
        val fieldTypes: MutableSet<Class<*>> = HashSet()
        fieldTypes.add(String::class.java)
        return Collections.unmodifiableSet(fieldTypes)
    }

    override fun getPrinter(annotation: DecryptRsa, fieldType: Class<*>): Printer<*> {
        return configureFormatterFrom(annotation)
    }

    override fun getParser(annotation: DecryptRsa, fieldType: Class<*>): Parser<*> {
        return configureFormatterFrom(annotation)
    }

    private fun configureFormatterFrom(annotation: DecryptRsa): Formatter<String> {
        return object : Formatter<String> {
            override fun print(text: String, locale: Locale): String {
                return text
            }

            @Throws(ParseException::class)
            override fun parse(text: String, locale: Locale): String {
                val rsaKeyCache = RsaKeyStore.getInstance().getRsaKeyCache()
                val currnetKey:PrivateKey? = RsaKeyStore.getInstance().getRsaKeyCache()?.currentRsaKeyPair?.privateKey
                val pulicKey:PublicKey? = RsaKeyStore.getInstance().getRsaKeyCache()?.currentRsaKeyPair?.publicKey
                val nextKey:PrivateKey? = RsaKeyStore.getInstance().getRsaKeyCache()?.nextRsaKeyPair?.privateKey
//                val plainTest = "test"
//                val encTest = CryptoRsaUtils.encryptMsg(plainTest, pulicKey!!)
//                val decTest = CryptoRsaUtils.decryptMsg(encTest,currnetKey!!)

                if(currnetKey!=null){
                    CryptoRsaUtils.decryptMsg(text, currnetKey)?.let{
                        return it
                    }
                }
                if(nextKey!=null){
                    CryptoRsaUtils.decryptMsg(text, nextKey)?.let{
                        return it
                    }
                }
                return ""
            }
        }
    }

}