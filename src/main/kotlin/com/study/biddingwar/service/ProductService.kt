package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.ProductDto
import com.study.biddingwar.domain.dto.ProductResultDto
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductService(private val productInfoService: ProductInfoService) {

    fun getProduct(id:Long): ProductResultDto {
        return productInfoService.getProduct(id)
    }

    fun getProducts(pageRequest: PageRequest, searchType:String?): List<ProductResultDto> {

        val products = if(searchType.isNullOrBlank()){
                            productInfoService.getProducts(pageRequest)
                        }else{
                            productInfoService.getProductsByGroup()
                            productInfoService.getProductsByName()
                        }

        return products
    }

    fun createProduct(productDto: ProductDto): ProductResultDto {
        return productInfoService.createProduct(productDto)
    }

    fun modifyProduct(id:Long, productDto: ProductDto): ProductResultDto {
        return productInfoService.modifyProduct(id, productDto)
    }

    fun deleteProduct(id:Long): ProductResultDto {
        return productInfoService.deleteProduct(id)
    }
}