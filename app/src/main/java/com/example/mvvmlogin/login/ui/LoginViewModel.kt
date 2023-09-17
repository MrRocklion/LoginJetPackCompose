package com.example.mvvmlogin.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class LoginViewModel : ViewModel(){
    private val _email = MutableLiveData<String>()
    val email:LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password:LiveData<String> = _password

    private val _loginEnable = MutableLiveData<Boolean>()
    val loginEnable:LiveData<Boolean> = _loginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email:String,password:String){
        _email.value = email
        _password.value = password
        _loginEnable.value = isValidEmail(email) && isValidPassWord(password)
    }
    // funciones para verificar     que tanto el email como la password ingresada son correctas
    private fun isValidPassWord(password: String): Boolean = password.length > 6
    private fun isValidEmail(email: String):Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    suspend fun onLoginSelected(){
        _isLoading.value = true
        delay(4000)
        _isLoading.value = false
    }

}