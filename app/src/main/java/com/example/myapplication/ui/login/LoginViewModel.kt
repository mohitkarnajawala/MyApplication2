package com.example.myapplication.ui.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.myapplication.data.LoginRepository
import com.example.myapplication.data.Result

import com.example.myapplication.R

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)
        Log.e("Login:result",""+result)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName.toString()))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    // get the registration result value
    fun register(username: String, password: String,name: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.register(username, password, name)
        Log.e("LoginViewModel:result",""+result)

        if (result is Result.Success) {
            Log.e("LoginViewModel:-","Success")
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName.toString()))
        } else {
            Log.e("LoginViewModel:-","Failed")
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        }
        else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    fun regDataChanged(username: String, password: String,name:String,confrmPsw:String){
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        }else if(name.isEmpty()){
            _loginForm.value= LoginFormState(nameError = R.string.name_field_empty)
        } else if(!isConfirmPasswordValid(password,confrmPsw)){
            _loginForm.value= LoginFormState(confrm_passwordError = R.string.invalid_confirm_password)
        }else{
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }


    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun isConfirmPasswordValid(password: String,confrmPsw: String):Boolean{
        return password==confrmPsw
    }

}