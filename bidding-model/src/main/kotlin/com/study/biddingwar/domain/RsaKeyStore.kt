package com.study.biddingwar.domain

import java.security.PrivateKey

class RsaKeyStore {
    private var rsaKeyCache:RsaKeyCache? = null

    private class HolderSingleton{
        companion object{
            val instance = RsaKeyStore()
        }
    }

    companion object{
        fun getInstance(): RsaKeyStore {
            return HolderSingleton.instance
        }
    }

    fun setRsaKeyCache(rsaKeyCache: RsaKeyCache){
        this.rsaKeyCache = rsaKeyCache
    }

    fun getRsaKeyCache(): RsaKeyCache? {
        return rsaKeyCache
    }

    fun getRsaPrivateKeys(): Map<String, PrivateKey?>? {
        val currnetKey: PrivateKey? = getRsaKeyCache()?.currentRsaKeyPair?.privateKey
        val nextKey: PrivateKey? = getRsaKeyCache()?.nextRsaKeyPair?.privateKey

        if (currnetKey != null && nextKey != null) {
            return mapOf(
                getRsaKeyCache()?.currentRsaKeyPair?.keyId.toString() to currnetKey,
                getRsaKeyCache()?.nextRsaKeyPair?.keyId.toString() to nextKey
            )
        } else if (currnetKey != null) {
            return mapOf(
                getRsaKeyCache()?.currentRsaKeyPair?.keyId.toString() to currnetKey
            )
        }
        return null
    }
}