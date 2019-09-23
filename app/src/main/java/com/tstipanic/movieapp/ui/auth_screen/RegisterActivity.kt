package com.tstipanic.movieapp.ui.auth_screen

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.tstipanic.movieapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        registerButton.setOnClickListener { registerUser() }
    }


    private fun registerUser() {
        val email = emailRegister.text.toString().trim()
        val password = registerPassword.text.toString().trim()
        val password2 = registerPasswordRe.text.toString().trim()

        if (email.isEmpty()) {
            emailRegister.error = "Email is required"
            emailRegister.requestFocus()
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRegister.error = "Enter a valid email"
            emailRegister.requestFocus()
        }

        if (password.isEmpty()) {
            registerPassword.error = "Password is required"
            registerPassword.requestFocus()
        }

        if (password.length < 6) {
            registerPassword.error = "Minimum lenght of password should be 6"
            registerPassword.requestFocus()
        }

        if (password != password2) {
            registerPasswordRe.error = "Password does not match"
            registerPasswordRe.requestFocus()
        } else {
            registerProgressBar.visibility = View.VISIBLE
            auth?.createUserWithEmailAndPassword(email, password)?.addOnCompleteListener {

                if (it.isSuccessful) {
                    Toast.makeText(applicationContext, "You have been successfully registered", Toast.LENGTH_LONG)
                        .show()
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else run {
                    if (it.exception is FirebaseAuthUserCollisionException) {
                        Toast.makeText(applicationContext, "This email is already registered", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(applicationContext, it.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                }
                registerProgressBar.visibility = View.GONE
            }
        }
    }
}