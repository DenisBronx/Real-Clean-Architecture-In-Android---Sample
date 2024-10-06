package com.denisbrandi.androidrealca.user.domain.model

sealed interface LoginError {
    data object InvalidEmail : LoginError
    data object InvalidPassword : LoginError
    data object GenericError : LoginError
    data object IncorrectCredentials : LoginError
}
