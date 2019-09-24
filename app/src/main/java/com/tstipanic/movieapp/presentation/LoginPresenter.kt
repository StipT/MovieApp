package com.tstipanic.movieapp.presentation

import com.google.firebase.auth.FirebaseAuth
import com.tstipanic.movieapp.ui.auth_screen.LoginContract

class LoginPresenter : LoginContract.Presenter {

    lateinit var auth: FirebaseAuth
    private lateinit var view: LoginContract.View


    override fun setView(view: LoginContract.View) {
        this.view = view
    }

    override fun signIn(email: String, password: String) {
        view.showProgress()
        auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                view.goToMainActivity()
            } else run {
                view.showToast(it.exception?.message!!)
                view.hideProgress()
            }
        }
    }

    override fun userLogin() {
        view.validateInfo()
    }
}