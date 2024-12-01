package com.alphaStore.user.feignClient

import com.alphaStore.user.model.PaginationResponse
import com.alphaStore.user.model.ProductResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "user-service", url = "http://localhost:8085/product")
interface ProductClient {

    @GetMapping("/getAll")
    fun showAllProducts() : PaginationResponse<ProductResponse>
}