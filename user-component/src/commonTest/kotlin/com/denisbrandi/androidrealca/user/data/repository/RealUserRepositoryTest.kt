package com.denisbrandi.androidrealca.user.data.repository

import com.denisbrandi.androidrealca.cache.test.TestCacheProvider
import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.httpclient.RealHttpClientProvider.createClient
import com.denisbrandi.androidrealca.user.data.model.JsonUserCacheDTO
import com.denisbrandi.androidrealca.user.domain.model.*
import com.denisbrandi.netmock.*
import com.denisbrandi.netmock.engine.NetMockEngine
import kotlin.test.*
import kotlinx.coroutines.test.runTest

class RealUserRepositoryTest {

    private val netMock = NetMockEngine()
    private val client = createClient(netMock)
    private val cacheProvider = TestCacheProvider("user-cache", JsonUserCacheDTO("", ""))
    private val sut = RealUserRepository(client, cacheProvider)

    @Test
    fun `EXPECT generic error WHEN response is 500`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 500
            }
        )

        val result = sut.login(LOGIN_REQUEST)

        assertEquals(Answer.Error(LoginError.GenericError), result)
    }

    @Test
    fun `EXPECT incorrect credentials WHEN response is 401`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 401
            }
        )

        val result = sut.login(LOGIN_REQUEST)

        assertEquals(Answer.Error(LoginError.IncorrectCredentials), result)
    }

    @Test
    fun `EXPECT generic error WHEN response is successful but response body is invalid`() =
        runTest {
            netMock.addMock(
                request = EXPECTED_REQUEST,
                response = {
                    code = 200
                    body = "[]"
                }
            )

            val result = sut.login(LOGIN_REQUEST)

            assertEquals(Answer.Error(LoginError.GenericError), result)
        }

    @Test
    fun `EXPECT success and data cached WHEN response is successful`() = runTest {
        netMock.addMock(
            request = EXPECTED_REQUEST,
            response = {
                code = 200
                mandatoryHeaders = RESPONSE_HEADERS
                body = RESPONSE_BODY
            }
        )

        val result = sut.login(LOGIN_REQUEST)

        assertEquals(Answer.Success(Unit), result)
        assertEquals(EXPECTED_CACHED_OBJECT, cacheProvider.providedCachedObject.get())
    }

    private companion object {
        val REQUEST_HEADERS = mapOf(
            "Accept" to "application/json",
            "Accept-Charset" to "UTF-8"
        )
        val RESPONSE_HEADERS = mapOf(
            "Content-Type" to "application/json"
        )
        val LOGIN_REQUEST = LoginRequest("valid@email.com", "12345678")
        const val REQUEST_BODY = """{
    "email": "valid@email.com",
    "password": "12345678"
}"""
        const val RESPONSE_BODY = """{
     "id": "AmazingAndroidDevId",
     "fullName": "Amazing Android Dev"
        }"""
        val EXPECTED_REQUEST = NetMockRequest(
            requestUrl = "https://api.unexisting.com/login",
            method = Method.Post,
            mandatoryHeaders = REQUEST_HEADERS,
            body = REQUEST_BODY
        )
        val EXPECTED_CACHED_OBJECT = JsonUserCacheDTO("AmazingAndroidDevId", "Amazing Android Dev")
    }
}
