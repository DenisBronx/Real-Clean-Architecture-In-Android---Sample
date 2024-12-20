package com.denisbrandi.androidrealca.onboarding.presentation.viewmodel

import com.denisbrandi.androidrealca.coroutines.testdispatcher.MainCoroutineRule
import com.denisbrandi.androidrealca.flow.testobserver.*
import com.denisbrandi.androidrealca.foundations.Answer
import com.denisbrandi.androidrealca.user.domain.model.*
import com.denisbrandi.androidrealca.user.domain.usecase.Login
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals

class RealLoginViewModelTest {

    @get:Rule
    val rule = MainCoroutineRule()

    private val login = TestLogin()
    private lateinit var stateObserver: FlowTestObserver<LoginScreenState>
    private lateinit var eventObserver: FlowTestObserver<LoginScreenEvent>
    private val sut = RealLoginViewModel(login, StateDelegate(), EventDelegate())

    @Before
    fun setUp() {
        stateObserver = sut.state.test()
        eventObserver = sut.viewEvent.test()
    }

    @Test
    fun `EXPECT default state WHEN initialized`() {
        assertEquals(listOf(LoginScreenState(DisplayState.Form)), stateObserver.getValues())
    }

    @Test
    fun `EXPECT loading state WHEN awaiting for use case`() = runTest {
        login.loginResult = { awaitCancellation() }

        sut.login(EMAIL, PASSWORD)

        assertEquals(
            listOf(
                LoginScreenState(DisplayState.Form),
                LoginScreenState(DisplayState.LoggingIn)
            ),
            stateObserver.getValues()
        )
    }

    @Test
    fun `EXPECT error event WHEN use case returns error`() = runTest {
        val loginError = LoginError.GenericError
        login.loginResult = { Answer.Error(loginError) }

        sut.login(EMAIL, PASSWORD)

        assertEquals(
            listOf(
                LoginScreenState(DisplayState.Form),
                LoginScreenState(DisplayState.LoggingIn),
                LoginScreenState(DisplayState.Form)
            ),
            stateObserver.getValues()
        )
        assertEquals(listOf(LoginScreenEvent.ShowError(loginError)), eventObserver.getValues())
    }

    @Test
    fun `EXPECT success event WHEN use case returns success`() = runTest {
        login.loginResult = { Answer.Success(Unit) }

        sut.login(EMAIL, PASSWORD)

        assertEquals(
            listOf(
                LoginScreenState(DisplayState.Form),
                LoginScreenState(DisplayState.LoggingIn)
            ),
            stateObserver.getValues()
        )
        assertEquals(listOf(LoginScreenEvent.SuccessfulLogin), eventObserver.getValues())
    }

    private class TestLogin : Login {
        lateinit var loginResult: suspend () -> Answer<Unit, LoginError>
        override suspend fun invoke(loginRequest: LoginRequest): Answer<Unit, LoginError> {
            return if (LoginRequest(EMAIL, PASSWORD) == loginRequest) {
                loginResult()
            } else {
                throw IllegalStateException("login not stubbed")
            }
        }
    }

    private companion object {
        const val EMAIL = "valid@email.com"
        const val PASSWORD = "12345678"
    }
}
