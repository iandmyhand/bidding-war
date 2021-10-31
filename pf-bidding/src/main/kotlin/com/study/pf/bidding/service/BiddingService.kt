package com.study.pf.bidding.service

import com.study.pf.bidding.domain.dto.BiddingInfoDto
import com.study.pf.bidding.domain.dto.GoodsInfoDto
import com.study.pf.bidding.domain.entity.Goods
import com.study.pf.bidding.repository.BiddingRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * 경매 종료일자 변경 불가
 * --> 변경하고 싶을 시 재등록 또는 물품 취소후 할 수 있도록 프로세스 진행
 * |
 * - 상품 경매 완료된 내역만 DB 에 쌓는것으로 현재 마무리 할 것
 * - 입찰가에 대해선 어떻게 돌려줄지? -> SKIP
 * - @TODO 8주차를 생략후 12, 13주를 먼저 진행하느라 입찰물품등록, 경매진행 프로세스가 부족함
 * |
 */
@Service
class BiddingService(private val biddingRepository: BiddingRepository) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * 해당 건은 경매장에서 이용자가 바로 즉시구매를 실행했을때 발생
     */
    @Transactional
    fun successBuyBid(biddingInfoDto: BiddingInfoDto): BiddingInfoDto {

        if (biddingInfoDto.biddingPrice!! != biddingInfoDto.biddingGoods.goodsBuyPrice!!) {
            throw Exception("biddingPrice | goodsBuyPrice check..")
        }

        val biddingInfo = this.biddingRepository.findById(biddingInfoDto.biddingId!!)
                .orElseThrow { NoSuchElementException("[successBuyBid] no such bidding : ${biddingInfoDto.biddingId}") }
        biddingInfo.setBiddingUser(biddingInfoDto.biddingUser)
        biddingInfo.setBiddingPrice(biddingInfoDto.biddingPrice)
        biddingInfo.setFinishing(true)

        return biddingRepository.save(biddingInfo).let {
            BiddingInfoDto(it.id!!,
            it.biddingGoods,
            it.biddingUser,
            it.biddingPrice)
        }
    }

    // 입찰하기
    
    // 경매물품 등록
    
    // 경매 만료일에 따른 종료

    // 경매 종료 상태값 변경 column : isFinishing (스케줄러 관련)
    fun setAutionStatus(/*goods: Goods*/) {
        // @TODO 스케줄러 logger로 테스트 후 11월 2째주 숙제때 보강할것
        /*val biddingInfo = this.biddingRepository.findById(goods.id!!)
                .orElseThrow { NoSuchElementException("[setAutionStatus] no such bidding goods : ${goods.id}") }

        biddingInfo.setFinishing(true)*/
        logger.info("scheduler working..!?")
    }
    
    // 만료되었으나 입찰한 유저가 없을 시 등록자에게 물품 반환
}