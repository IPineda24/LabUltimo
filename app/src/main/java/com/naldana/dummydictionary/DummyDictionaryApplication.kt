package com.naldana.dummydictionary

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.RestrictTo
import com.naldana.dummydictionary.data.DummyDictionaryDatabase
import com.naldana.dummydictionary.network.RetrofitInstance
import com.naldana.dummydictionary.repository.DictionaryRepository
import com.naldana.dummydictionary.repository.LoginRepository

class DummyDictionaryApplication : Application() {
     private val prefs: SharedPreferences by lazy {
         getSharedPreferences("DummyDictionary", Context.MODE_PRIVATE)
     }
    private  val dataBase by lazy {
        DummyDictionaryDatabase.getInstance(this)
    }
    private fun getAPIService() = with(RetrofitInstance){
        setToken(getToken())
        getWordServices()
    }

    fun getDictionaryRepository() =
        DictionaryRepository(dataBase, getAPIService())

    fun getLoginRepository() =
        LoginRepository(getAPIService())

    private fun getToken(): String = prefs.getString(USER_TOKEN,"")!!

    fun isUserLogin() = getToken() != ""

    fun saveAuthToken (token: String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }
    companion object{
        const val USER_TOKEN = "user_token"
    }


}