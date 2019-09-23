package com.tstipanic.movieapp.ui.auth_screen

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.ui.grid_screen.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        loginButton.setOnClickListener { userLogin() }
        registerNowText.setOnClickListener { goToRegisterActivity() }
    }

    private fun goToRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))

    }

    private fun userLogin() {
        val email = loginEmail.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            loginEmail.error = "Enter valid email"
            loginEmail.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.error = "Enter a valid email"
            loginEmail.requestFocus()
        }
        if (password.isEmpty()) {
            passwordEditText.error = "Password is required"
            passwordEditText.requestFocus()
        }
        if (password.length < 6) {
            passwordEditText.error = "Minimum lenght for a password should be 6"
            passwordEditText.requestFocus()
        } else {
            progressBar.visibility = View.VISIBLE
            auth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    getSharedPreferences("login", 0).edit().putBoolean("isLoggedIn", true).apply()
                    startActivity(intent)
                } else run { Toast.makeText(applicationContext, it.exception!!.message, Toast.LENGTH_LONG).show() }
                progressBar.visibility = View.GONE
            }

        }
    }
}