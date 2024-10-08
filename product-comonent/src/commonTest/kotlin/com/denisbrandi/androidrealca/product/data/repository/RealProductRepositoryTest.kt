package com.denisbrandi.androidrealca.product.data.repository

import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider.createClient
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.netmock.*
import com.denisbrandi.netmock.engine.NetMockEngine
import kotlin.test.*
import kotlinx.coroutines.test.runTest

class RealProductRepositoryTest {

    private val netMock = NetMockEngine()
    private val client = createClient(netMock)

    private val sut = RealProductRepository(client)

    @Test
    fun `EXPECT error WHEN request fails`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 500
            }
        )

        val result = sut.getProducts()

        assertEquals(Answer.Error(Unit), result)
    }

    @Test
    fun `EXPECT list of products WHEN request succeeds`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 200
                mandatoryHeaders = RESPONSE_HEADERS
                body = PRODUCTS_RESPONSE
            }
        )

        val result = sut.getProducts()

        assertEquals(Answer.Success(EXPECTED_PRODUCTS), result)
    }

    @Test
    fun `EXPECT error WHEN parsing fails`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 200
                mandatoryHeaders = RESPONSE_HEADERS
                body = "{}"
            }
        )

        val result = sut.getProducts()

        assertEquals(Answer.Error(Unit), result)
    }

    private companion object {
        val REQUEST_HEADERS = mapOf(
            "Accept" to "application/json",
            "Accept-Charset" to "UTF-8"
        )
        val RESPONSE_HEADERS = mapOf(
            "Content-Type" to "application/json"
        )
        val EXPECTED_REQUEST = NetMockRequest(
            requestUrl = "https://api.unexisting.com/products",
            method = Method.Get,
            mandatoryHeaders = REQUEST_HEADERS
        )
        val EXPECTED_PRODUCTS = listOf(
            Product(
                id = "1",
                name = "Wireless Headphones",
                money = Money(99.99, "$"),
                imageUrl = "https://example.com/images/wireless-headphones.jpg",
                quantityAvailable = 50
            ),
            Product(
                id = "2",
                name = "Smartphone Stand",
                money = Money(15.49, "$"),
                imageUrl = "https://example.com/images/smartphone-stand.jpg",
                quantityAvailable = 150
            )
        )
        const val PRODUCTS_RESPONSE = """[
  {
    "id": 1,
    "name": "Wireless Headphones",
    "price": 99.99,
    "currency": "$",
    "imageUrl": "https://example.com/images/wireless-headphones.jpg",
    "quantityAvailable": 50
  },
  {
    "id": 2,
    "name": "Smartphone Stand",
    "price": 15.49,
    "currency": "$",
    "imageUrl": "https://example.com/images/smartphone-stand.jpg",
    "quantityAvailable": 150
  }
  ]"""
    }
}
