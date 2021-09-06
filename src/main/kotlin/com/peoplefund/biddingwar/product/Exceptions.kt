package com.peoplefund.biddingwar.product

import kotlin.RuntimeException


class NoRegisteredProduct: RuntimeException()

class AlreadyExistUserException: RuntimeException()

class NoMatchedSignInException: RuntimeException()