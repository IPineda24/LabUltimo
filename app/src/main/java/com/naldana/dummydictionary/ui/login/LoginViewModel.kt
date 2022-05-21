package com.naldana.dummydictionary.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naldana.dummydictionary.network.ApiResponse
import com.naldana.dummydictionary.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class LoginViewModel(private  val repository: LoginRepository) : ViewModel() {
    val userField = MutableLiveData("")
    val passwordField = MutableLiveData("")
    private  val _status = MutableLiveData<LoginUiStatus>(LoginUiStatus.Resume)
    val satatus: LiveData<LoginUiStatus>
        get() = _status

    fun onLogin(){
        _status.value = LoginUiStatus.Loading
        viewModelScope.launch(Dispatchers.IO){
            _status.postValue(
                when (val response=
                    repository.login(userField.value.toString(), passwordField.value.toString())){
                    is ApiResponse.Error -> LoginUiStatus.Error(response.exception)
                    is ApiResponse.ErrorWithMessage -> LoginUiStatus.Resume
                    is ApiResponse.Success -> LoginUiStatus.Success(response.data)
                }
            )
        }
    }



}