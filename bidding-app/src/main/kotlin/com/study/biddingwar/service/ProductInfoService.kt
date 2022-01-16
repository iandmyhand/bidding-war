package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.ProductDto
import com.study.biddingwar.domain.dto.ProductResultDto
import com.study.biddingwar.domain.entity.ProductInfo
import com.study.biddingwar.exception.NotPermissionException
import com.study.biddingwar.repository.ProductInfoRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@Service
class ProductInfoService(private val productInfoRepository: ProductInfoRepository) {

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    fun getProduct(id:Long): ProductResultDto{
        val productInfo = productInfoRepository.findById(id)
            .orElseThrow { NoSuchElementException("No found productId: $id") }

        return ProductResultDto(
            productInfo.id!!,
            productInfo.productGroup,
            productInfo.productName,
            productInfo.productDesc,
            productInfo.productPrice,
            productInfo.userId?:0,
            productInfo.bidStatus
        )
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    fun getProducts(pageRequest: PageRequest): List<ProductResultDto> {
        return productInfoRepository.findAll(pageRequest).map {
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice, it.userId?:0, it.bidStatus)
        }.toList()
    }

    fun getProductsByGroup():List<ProductResultDto>{
        return listOf<ProductResultDto>()
    }

    fun getProductsByName():List<ProductResultDto>{
        return listOf<ProductResultDto>()
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun createProduct(productDto: ProductDto): ProductResultDto {

        val productInfo = ProductInfo(
            productGroup = productDto.productGroup,
            productName = productDto.productName,
            productPrice = productDto.productPrice,
            productDesc = productDto.productDesc,
            userId = productDto.userId
        )

        return productInfoRepository.save(productInfo).let{
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice, it.userId?:0, it.bidStatus)
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun modifyBidStatus(productId:Long, userId:Long, status:String){
        val productInfo = productInfoRepository.findById(productId)
            .orElseThrow { NoSuchElementException("No found productId: $productId")}

        if(productInfo.userId != userId)
            throw NotPermissionException("not creation by user")

        productInfo.bidStatus = status
    }

    @Transactional(propagation = Propagation.REQUIRED)
    fun modifyProduct(id:Long, productDto: ProductDto): ProductResultDto {
        val productInfo = productInfoRepository.findById(id)
            .orElseThrow { NoSuchElementException("No found productId: $id")}

        productInfo.setProductGroup(productDto.productGroup)
        productInfo.setProductName(productDto.productName)
        productInfo.setProductPrice(productDto.productPrice)
        productInfo.setProductDesc(productDto.productDesc)

        return productInfoRepository.save(productInfo).let{
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice, it.userId?:0, it.bidStatus)
        }
    }

    fun deleteProduct(id:Long): ProductResultDto {
        val productInfo = productInfoRepository.findById(id)
            .orElseThrow { NoSuchElementException("No found productId: $id")}
        productInfoRepository.delete(productInfo)

        return ProductResultDto(
            productInfo.id!!,
            productInfo.productGroup,
            productInfo.productName,
            productInfo.productDesc,
            productInfo.productPrice,
            productInfo.userId?:0,
            productInfo.bidStatus)
    }
}
