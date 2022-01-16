package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.BiddingDto
import com.study.biddingwar.domain.dto.BiddingResultDto
import com.study.biddingwar.domain.entity.BiddingInfo
import com.study.biddingwar.exception.NotPermissionException
import org.springframework.stereotype.Service

@Service
class BiddingService(private val productInfoService: ProductInfoService,
                     private val biddingInfoService: BiddingInfoService) {

    fun bidOnProduct(biddingDto: BiddingDto): BiddingInfo {

        val product = productInfoService.getProduct(biddingDto.productId)

        //상품 등록한 사용자가 입찰 불가능
        if(product.userId != biddingDto.userId)
            throw NotPermissionException("product not bid by creation product user")

        //기본가격 보다 같거나 작게 입찰 불가능
        if(product.productPrice <= biddingDto.biddingPrice)
            throw RuntimeException("not bid product over basic price")

        //경매가 끝난거면 입찰 할수 없음
        if(product.bidStatus == "C")
            throw RuntimeException("this product complete bidding")

        return biddingInfoService.bidOnProduct(biddingDto)
    }

    //제품의 입찰자 목록 조회
    fun getProductForBidding(productId:Long): List<BiddingResultDto> {
        return biddingInfoService.getProductForBidding(productId)
    }

    //입찰한 사용자 정보 조회
    fun getBiddingWithProductAndUser(productId:Long, userId:Long): BiddingResultDto {
        return biddingInfoService.getBiddingWithProductAndUser(productId, userId)
    }

    //입찰 종료
    fun completeBiddingProduct(productId:Long, userId: Long) {
        productInfoService.modifyBidStatus(productId, userId, "C")
    }

    fun modifyBiddingProduct(biddingId:Long, biddingDto: BiddingDto) {
        biddingInfoService.modifyBiddingProduct(biddingId, biddingDto)
    }

    fun cancelBiddingProduct(biddingId:Long){
        biddingInfoService.cancelBiddingProduct(biddingId)
    }
}