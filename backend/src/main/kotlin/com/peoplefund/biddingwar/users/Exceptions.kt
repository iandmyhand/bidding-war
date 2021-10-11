package com.peoplefund.biddingwar.users

import kotlin.RuntimeException

class AlreadyExistUserException: RuntimeException()

class NoMatchedSignInException: RuntimeException()