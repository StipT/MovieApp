package com.tstipanic.movieapp.ui.grid_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.ui.auth_screen.LoginActivity
import kotlinx.android.synthetic.main.dialog_fragment_logout.*

class LogoutFragment : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_logout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firebaseAuth = FirebaseAuth.getInstance()
        emailText.text = firebaseAuth.currentUser?.email.toString()

        cancelButton.setOnClickListener { dismiss() }
        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        Toast.makeText(requireContext(), "You have been logged out", Toast.LENGTH_SHORT).show()
        activity?.getSharedPreferences("login", 0)?.edit()?.putBoolean("isLoggedIn", false)?.apply()
        startActivity(intent)
    }
}