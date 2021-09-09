package com.study.biddingwar.exception

class CsrfTokenException(message: String, csrfToken:String) : RuntimeException(message){

    var csrfToken = csrfToken
        get() { return this.csrfToken}

}