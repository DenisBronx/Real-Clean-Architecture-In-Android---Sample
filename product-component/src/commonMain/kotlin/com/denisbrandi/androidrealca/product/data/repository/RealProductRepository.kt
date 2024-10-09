package com.denisbrandi.androidrealca.product.data.repository

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.product.data.model.JsonProductResponseDTO
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.product.domain.repository.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

internal class RealProductRepository(
    private val httpClient: HttpClient
) : ProductRepository {
    override suspend fun getProducts(): Answer<List<Product>, Unit> {
        val response = httpClient.get("https://api.unexisting.com/products") {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
        }
        return if (response.status.isSuccess()) {
            handleSuccessfulProductsResponse(response)
        } else {
            Answer.Error(Unit)
        }
    }

    private suspend fun handleSuccessfulProductsResponse(httpResponse: HttpResponse): Answer<List<Product>, Unit> {
        return try {
            val responseBody = httpResponse.body<List<JsonProductResponseDTO>>()
            Answer.Success(mapProducts(responseBody))
        } catch (t: Throwable) {
            Answer.Error(Unit)
        }
    }

    private fun mapProducts(jsonProducts: List<JsonProductResponseDTO>): List<Product> {
        return jsonProducts.map { jsonProduct ->
            Product(
                jsonProduct.id.toString(),
                jsonProduct.name,
                Money(jsonProduct.price, jsonProduct.currency),
                jsonProduct.imageUrl
            )
        }
    }
}
