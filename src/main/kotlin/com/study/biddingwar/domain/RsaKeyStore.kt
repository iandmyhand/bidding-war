package com.study.biddingwar.domain

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
}