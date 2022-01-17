package com.study.biddingwar.service

import com.study.biddingwar.domain.aggregate.BiddingAggregate
import com.study.biddingwar.domain.dto.BiddingDto
import com.study.biddingwar.domain.dto.BiddingResultDto
import com.study.biddingwar.domain.entity.BiddingInfo
import com.study.biddingwar.exception.DataDuplicationException
import com.study.biddingwar.repository.BiddingAggregateRepository
import com.study.biddingwar.repository.BiddingInfoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Service
class BiddingService(private val biddingInfoRepository: BiddingInfoRepository,
                     private val biddingAggregateRepository: BiddingAggregateRepository) {

    @Transactional(propagation = Propagation.REQUIRED)
    fun bidOnProduct(biddingDto: BiddingDto): BiddingInfo {


        var biddingInfo = BiddingInfo(
            biddingDto.productId,
            biddingDto.userId!!,
            biddingDto.biddingPrice
            )
        try{
            biddingInfo = biddingInfoRepository.save(biddingInfo)
        }catch (e:DataIntegrityViolationException){
            throw DataDuplicationException("data duplication productId:${biddingDto.productId}, " +
                    "userId:${biddingDto.userId}")
        }

        return biddingInfo
    }

    fun getProductForBidding(productId:Long): List<BiddingResultDto> {
        val results:List<BiddingAggregate> = biddingAggregateRepository.findAllByProductInfoId(productId)

        if(results.isEmpty()){
            throw NoSuchElementException("No Data Not Found")
        }

        val resultDtos:List<BiddingResultDto> = results.map{
            it -> BiddingResultDto(
                biddingId = it.id!!,
                productId = it.productInfo!!.id!!,
                productName = it.productInfo!!.productName,
                userId = it.user!!.id!!,
                userName = it.user!!.userName,
                biddingPrice = it.biddingPrice,
                biddingAt = it.createAt
            )
        }

        return resultDtos
    }

    fun getBiddingWithProductAndUser(productId:Long, userId:Long): BiddingResultDto {
        val result = biddingAggregateRepository.findByProductInfoIdAndUserId(productId, userId)?:
            throw NoSuchElementException("No Data Not Found")

        val resultDto = result.let {
            BiddingResultDto(
                biddingId = it.id!!,
                productId = it.productInfo!!.id!!,
                productName = it.productInfo!!.productName,
                userId = it.user!!.id!!,
                userName = it.user!!.userName,
                biddingPrice = it.biddingPrice,
                biddingAt = it.createAt
            )
        }

        return resultDto

    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun modifyBiddingProduct(biddingId:Long, biddingDto: BiddingDto){
        val biddingInfo = biddingInfoRepository.findById(biddingId).get()
        biddingInfo.biddingPrice = biddingDto.biddingPrice
        biddingInfo.productId = biddingDto.productId
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun cancelBiddingProduct(biddingId:Long){
        val biddingInfo = biddingInfoRepository.findById(biddingId).get()
        biddingInfoRepository.delete(biddingInfo)
    }
}