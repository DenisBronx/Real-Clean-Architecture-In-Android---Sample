package com.denisbrandi.androidrealca.user.data.repository

import com.denisbrandi.androidrealca.cache.*
import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.httpclient.AccessTokenProvider
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
        cacheProvider.getCachedObject(
            fileName = "user-cache",
            serializer = JsonUserCacheDTO.serializer(),
            defaultValue = DEFAULT_USER
        )
    }

    override suspend fun login(loginRequest: LoginRequest): Answer<Unit, LoginError> {
        return try {
            val response =
                client.post("https://api.json-generator.com/templates/Q7s_NUVpyBND/data") {
                    headers {
                        append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                        val accessTokenHeader = AccessTokenProvider.getAccessTokenHeader()
                        append(accessTokenHeader.first, accessTokenHeader.second)
                    }
                    setBody(JsonLoginRequestDTO(loginRequest.email, loginRequest.password))
                }
            if (response.status.isSuccess()) {
                handleSuccessfulLoginResponse(response)
            } else {
                handleFailingLoginResponse(response)
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            Answer.Error(LoginError.GenericError)
        }
    }

    private suspend fun handleSuccessfulLoginResponse(httpResponse: HttpResponse): Answer<Unit, LoginError> {
        val responseBody = httpResponse.body<JsonLoginResponseDTO>()
        cachedObject.put(JsonUserCacheDTO(responseBody.id, responseBody.fullName))
        return Answer.Success(Unit)
    }

    private fun handleFailingLoginResponse(httpResponse: HttpResponse): Answer<Unit, LoginError> {
        val error = if (httpResponse.status.value == 401) {
            LoginError.IncorrectCredentials
        } else {
            LoginError.GenericError
        }
        return Answer.Error(error)
    }

    override fun getUser(): User {
        val cachedUser = cachedObject.get()
        return User(cachedUser.id, cachedUser.fullName)
    }

    override fun isLoggedIn(): Boolean {
        return cachedObject.get() != DEFAULT_USER
    }

    companion object {
        val DEFAULT_USER = JsonUserCacheDTO("", "")
    }
}
