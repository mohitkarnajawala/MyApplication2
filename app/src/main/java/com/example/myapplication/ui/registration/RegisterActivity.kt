package com.example.myapplication.ui.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Constant.AppConstant
import com.example.myapplication.Constant.showToastMessage
import com.example.myapplication.MainDraweraActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityRegistrationBinding
import com.example.myapplication.ui.login.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {

   private lateinit var binding:ActivityRegistrationBinding
   private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUIFunctions()

    }

    private fun initUIFunctions() {
        //hide the action Bar
        supportActionBar?.hide()


       // Initialize Firebase Auth
        auth = Firebase.auth

        //set id's of UI components in layouts via binding
        val name=binding.name
        val username=binding.username
        val password=binding.password
        val confirmPassword=binding.cnfrmPassword
        val signupButton=binding.register
        val loading=binding.loading

        //create and initialize the object of view model
        loginViewModel= ViewModelProvider(this@RegisterActivity,
            LoginViewModelFactory()).get(LoginViewModel::class.java)

        //observe the live data of login form states via Observer
        loginViewModel.loginFormState.observe(this, Observer {
            val loginStates=it?:return@Observer
            if(loginStates.nameError==null){
                name.error= loginStates.nameError?.let {
                        it1 -> getString(it1)
                }
            }
            if (loginStates.usernameError != null) {
                username.error = getString(loginStates.usernameError)
            }
            if (loginStates.passwordError != null) {
                password.error = getString(loginStates.passwordError)
            }

            if(loginStates.confrm_passwordError!=null){
                confirmPassword.error=getString(loginStates.confrm_passwordError)
            }
        })

        //Observe the live data of login result via Observer either it is success or error
        loginViewModel.loginResult.observe(this@RegisterActivity, Observer {
            val loginResult = it ?: return@Observer


            if (loginResult.error != null) {
                loading.visibility = View.GONE
                Log.e("RegisterActivity:Error","True")
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                loading.visibility = View.GONE
                Log.e("RegisterActivi:Success","True")
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            // finish()
        })

        name.afterTextChanged {
            loginViewModel.regDataChanged(username = username.text.toString(),
                name=name.text.toString(),
                password = password.text.toString(),
            confrmPsw = confirmPassword.text.toString())
        }

        username.afterTextChanged {
            loginViewModel.regDataChanged(username = username.text.toString(),
                name=name.text.toString(),
                password = password.text.toString(),
                confrmPsw = confirmPassword.text.toString())
        }

        password.apply {
            afterTextChanged {
                loginViewModel.regDataChanged(username = username.text.toString(),
                    name=name.text.toString(),
                    password = password.text.toString(),
                    confrmPsw = confirmPassword.text.toString())
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.register(
                            username.text.toString(),
                            password.text.toString(),
                            name.text.toString()

                        )
                }
                false
            }

            signupButton.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.register(username.text.toString(), password.text.toString(),name.text.toString())
            }
        }

    }

    override fun onStart() {
        super.onStart()
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience

        Log.e("DisplayName:-",""+displayName)
        val intent= Intent(this, MainDraweraActivity::class.java)
        intent.putExtra(AppConstant.username,displayName)
        startActivity(intent)
        showToastMessage("$welcome $displayName", Toast.LENGTH_LONG)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        showToastMessage("Error:${errorString.toString()}")
    }

    fun goToSignIn(view: View) {
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
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
}