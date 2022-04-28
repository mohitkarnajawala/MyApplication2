package com.example.myapplication.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.Constant.AppConstant
import com.example.myapplication.Constant.showToastMessage
import com.example.myapplication.MainDraweraActivity
import com.example.myapplication.databinding.ActivityLoginBinding

import com.example.myapplication.R
import com.example.myapplication.session.Session
import com.example.myapplication.ui.registration.RegisterActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import java.util.*
import com.facebook.FacebookException

import com.facebook.FacebookCallback




class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbackManager: CallbackManager
    private val EMAIL = "email"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Initialize the id to variables
        val username = binding.username
        val password = binding.password

        val login = binding.login
        val loading = binding.loading
        val signup= binding.tvSignUp

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity,
            Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer


            if (loginResult.error != null) {
                loading.visibility = View.GONE
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                loading.visibility = View.GONE
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
           // finish()
        })

        signup?.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            finish()
        }

        username.afterTextChanged {
            loginViewModel.loginDataChanged(username = username.text.toString(), password = password.text.toString())
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(username.text.toString(), password.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }

           /* binding.loginButton?.setOnClickListener{
                binding.loginButton?.setReadPermissions(listOf(EMAIL))
                callbackManager = CallbackManager.Factory.create()
                binding.loginButton?.registerCallback(callbackManager, object :
                    FacebookCallback<LoginResult?> {
                    override fun onSuccess(loginResult: LoginResult?) {
                        Log.d("MainActivity", "Facebook token: " + loginResult!!.accessToken.token)
                        startActivity(
                            Intent(
                                applicationContext,
                                MainDraweraActivity::class.java
                            )
                        )// App code
                    }
                    override fun onCancel() { // App code
                    }
                    override fun onError(exception: FacebookException) { // App code
                    }
                })

                callbackManager = CallbackManager.Factory.create()
                with(LoginManager) {
                    getInstance().registerCallback(callbackManager,
                                object : FacebookCallback<LoginResult?> {
                                    override fun onSuccess(loginResult: LoginResult?) { // App code
                                    }

                                    override fun onCancel() { // App code
                                    }

                                    override fun onError(exception: FacebookException) { // App code
                                    }

                                    val accessToken = AccessToken.getCurrentAccessToken()
                                    accessToken != null && !accessToken.isExpired
                                })
                }
            }*/

            callbackManager = CallbackManager.Factory.create()
            binding.loginButton?.setReadPermissions(Arrays.asList(EMAIL))

            // Callback registration
            // Callback registration
            binding.loginButton?.registerCallback(
                callbackManager, object : FacebookCallback<LoginResult?> {

                    override fun onCancel() {
                        // App code
                        Log.e("LoginACtivity","cancel")
                    }

                    override fun onError(exception: FacebookException) {
                        // App code
                        Log.e("LoginACtivity",""+exception)
                    }

                    override fun onSuccess(result: LoginResult?) {
                        Log.e("LoginACtivity",""+result)
                        startActivity(
                            Intent(
                                applicationContext,
                                MainDraweraActivity::class.java
                            )
                        )
                    }
                })
        }
    }


    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience



        val intent= Intent(this,MainDraweraActivity::class.java)
        intent.putExtra(AppConstant.username,displayName)
        startActivity(intent)
        showToastMessage("$welcome $displayName",Toast.LENGTH_LONG)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        showToastMessage(errorString.toString())
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}