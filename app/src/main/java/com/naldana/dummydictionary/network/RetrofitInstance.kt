package com.naldana.dummydictionary.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "http://10.0.2.2:3000/"
object RetrofitInstance {
    private val interceptorLogging =HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private var token = ""

    fun setToken(value: String){
        token = value
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .addInterceptor{ chain ->
                    chain.proceed(
                        chain
                            .request()
                            .newBuilder()
                            .addHeader("Authorization", "BEARER $token")
                            .build()
                    )
                }.addInterceptor(interceptorLogging)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWordServices(): WordService{
        return retrofit.create(WordService::class.java)
    }
}