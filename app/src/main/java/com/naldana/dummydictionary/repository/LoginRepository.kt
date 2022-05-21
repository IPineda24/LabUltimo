package com.naldana.dummydictionary.repository

import androidx.lifecycle.MutableLiveData
import com.naldana.dummydictionary.network.ApiResponse
import com.naldana.dummydictionary.network.WordService
import com.naldana.dummydictionary.network.dto.LoginRequest
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val api: WordService) {
    suspend fun login(username: String, password: String): ApiResponse<String> {
        try {
            val response = api.login(LoginRequest(username, password))
            return ApiResponse.Success(response.token)

        } catch (e: HttpException) {
            if (e.code() == 400) {
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        } catch (e: IOException){
            return ApiResponse.Error(e)

        }
    }
}