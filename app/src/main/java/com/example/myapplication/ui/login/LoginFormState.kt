package com.example.myapplication.ui.login

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    val nameError: Int?=null,
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val confrm_passwordError:Int?=null,
    val isDataValid: Boolean = false
)