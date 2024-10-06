package com.denisbrandi.androidrealca.user.data.repository

import com.denisbrandi.androidrealca.cache.*
import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.data.model.*
import com.denisbrandi.androidrealca.user.domain.model.*
import com.denisbrandi.androidrealca.user.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.*

internal class RealUserRepository(
    private val client: HttpClient,
    cacheProvider: CacheProvider
) : UserRepository {

    private val cachedObject: CachedObject<JsonUserCacheDTO> by lazy {
        cacheProvider.getCachedObject("user-cache", JsonUserCacheDTO("", ""))
    }

    override suspend fun login(loginRequest: LoginRequest): Answer<Unit, LoginError> {
        val response = client.post("https://api.unexisting.com/login") {
            headers {
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
            setBody(JsonLoginRequestDTO(loginRequest.email, loginRequest.password))
        }
        return if (response.status.isSuccess()) {
            handleSuccessfulLoginResponse(response)
        } else {
            handleFailingLoginResponse(response)
        }
    }

    private suspend fun handleSuccessfulLoginResponse(httpResponse: HttpResponse): Answer<Unit, LoginError> {
        return try {
            val responseBody = httpResponse.body<JsonLoginResponseDTO>()
            cachedObject.put(JsonUserCacheDTO(responseBody.id, responseBody.fullName))
            Answer.Success(Unit)
        } catch (t: Throwable) {
            t.printStackTrace()
            Answer.Error(LoginError.GenericError)
        }
    }

    private fun handleFailingLoginResponse(httpResponse: HttpResponse): Answer<Unit, LoginError> {
        val error = if (httpResponse.status.value == 401) {
            LoginError.IncorrectCredentials
        } else {
            LoginError.GenericError
        }
        return Answer.Error(error)
    }
}
