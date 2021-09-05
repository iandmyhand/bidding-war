package com.study.biddingwar.domain

class RsaKeyCache {
    var currentRsaKeyPair: RsaKey
    var nextRsaKeyPair: RsaKey? = null

    constructor(currentRsaKeyPair: RsaKey, nextRsaKeyPair: RsaKey?=null){
        this.currentRsaKeyPair = currentRsaKeyPair
        this.nextRsaKeyPair = nextRsaKeyPair
    }
}