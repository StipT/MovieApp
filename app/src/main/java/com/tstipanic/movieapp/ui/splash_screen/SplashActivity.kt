package com.tstipanic.movieapp.ui.splash_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tstipanic.movieapp.ui.auth_screen.LoginActivity
import com.tstipanic.movieapp.ui.grid_screen.MainActivity

class SplashActivity : AppCompatActivity() {

    private fun isLoggedIn(): Boolean {
        val sharedPreferences = baseContext.getSharedPreferences("login", 0)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }


    private fun goToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun goToAuthActivity() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isLoggedIn()) {
            goToMainActivity()
        } else {
            goToAuthActivity()
        }
    }
}