package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.ProductDto
import com.study.biddingwar.domain.dto.ProductResultDto
import com.study.biddingwar.domain.entity.ProductInfo
import com.study.biddingwar.repository.ProductInfoRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
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
            productInfo.productPrice)
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    fun getProducts(pageRequest: PageRequest): List<ProductResultDto> {
        return productInfoRepository.findAll(pageRequest).map {
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice)
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
            productDesc = productDto.productDesc
        )

        return productInfoRepository.save(productInfo).let{
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice)
        }
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
            ProductResultDto(it.id!!, it.productGroup, it.productName, it.productDesc, it.productPrice)
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
            productInfo.productPrice)
    }
}
