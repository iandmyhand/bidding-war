package com.study.peoplefund.domain.vo

enum class BiddingStatus(
    private val value: String
) {
    SUCCESS("낙찰"),
    FAILURE("취소"),
    IN_PROGRESS("진행 중");

    fun getValue(): String {
        return this.value
    }

    override fun toString(): String {
        return "낙찰 상태: [$value]"
    }

    companion object {
        fun of(value: String): BiddingStatus {
            return values().first { status -> status.value == value }
        }
    }
}