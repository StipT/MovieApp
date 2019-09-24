package com.tstipanic.movieapp.ui.auth_screen

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.ui.grid_screen.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter by inject<LoginContract.Presenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.setView(this)
        loginButton.setOnClickListener { presenter.userLogin() }
        registerNowText.setOnClickListener { goToRegisterActivity() }

        val editorListener: TextView.OnEditorActionListener = TextView.OnEditorActionListener { _, _, _ ->
            presenter.userLogin()
            false
        }
        passwordEditText.setOnEditorActionListener(editorListener)
    }

    override fun goToRegisterActivity() = startActivity(Intent(this, RegisterActivity::class.java))


    override fun validateInfo() {
        val email = loginEmail.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            loginEmail.error = getString(R.string.login_error_email)
            loginEmail.requestFocus()
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.error = getString(R.string.login_error_email)
            loginEmail.requestFocus()
        }
        if (password.isEmpty()) {
            passwordEditText.error = getString(R.string.login_error_password)
            passwordEditText.requestFocus()
        }
        if (password.length < 6) {
            passwordEditText.error = getString(R.string.login_error_short_password)
            passwordEditText.requestFocus()
        } else {
            presenter.signIn(email, password)


        }
    }

    override fun showToast(message: String) = Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()


    override fun showProgress() {
        progressBar.progress = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.progress = View.GONE
    }

    override fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        getSharedPreferences("login", 0).edit().putBoolean("isLoggedIn", true).apply()
        startActivity(intent)
    }
}