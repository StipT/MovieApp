package com.tstipanic.movieapp.ui.auth_screen

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tstipanic.movieapp.R
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val presenter by inject<RegisterContract.Presenter>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter.setView(this)
        registerButton.setOnClickListener { presenter.userRegister() }

        val editorListener: TextView.OnEditorActionListener = TextView.OnEditorActionListener { _, _, _ ->
            presenter.userRegister()
            false
        }
        registerPasswordRe.setOnEditorActionListener(editorListener)
    }


    override fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun validateInfo() {
        val email = emailRegister.text.toString().trim()
        val password = registerPassword.text.toString().trim()
        val password2 = registerPasswordRe.text.toString().trim()

        if (email.isEmpty()) {
            emailRegister.error = getString(R.string.error_email_required)
            emailRegister.requestFocus()
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailRegister.error = getString(R.string.login_error_email)
            emailRegister.requestFocus()
        }

        if (password.isEmpty()) {
            registerPassword.error = getString(R.string.login_error_password)
            registerPassword.requestFocus()
        }

        if (password.length < 6) {
            registerPassword.error = getString(R.string.login_error_short_password)
            registerPassword.requestFocus()
        }

        if (password != password2) {
            registerPasswordRe.error = getString(R.string.error_password_match)
            registerPasswordRe.requestFocus()
        } else {
            presenter.signUp(email, password)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        registerProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        registerProgressBar.visibility = View.GONE
    }
}