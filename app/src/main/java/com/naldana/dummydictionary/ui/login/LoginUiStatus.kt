package com.naldana.dummydictionary.ui.login

import java.lang.Exception

sealed class LoginUiStatus {

    object Resume: LoginUiStatus()
    object  Loading : LoginUiStatus()
    class Error(val exception: Exception): LoginUiStatus()
    data class Success(val token: String) : LoginUiStatus()
}