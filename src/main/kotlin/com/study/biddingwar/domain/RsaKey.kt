package com.study.biddingwar.domain

import com.study.biddingwar.common.crypto.CryptoRsaUtils
import java.security.PrivateKey
import java.security.PublicKey

class RsaKey {
    var keyId:Long? = 0
    var privateKey:PrivateKey
        private set
    var publicKey:PublicKey
        private set

    constructor(keyId:Long?=0, privateKey: String, publicKey: String){
        println("keyId: $keyId, publickey: $publicKey")
        this.keyId = keyId
        this.privateKey = CryptoRsaUtils.getPrivateKey(privateKey)
        this.publicKey = CryptoRsaUtils.getPublicKey(publicKey)
    }
}