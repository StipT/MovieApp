package com.tstipanic.movieapp.presentation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.tstipanic.movieapp.ui.auth_screen.RegisterContract


class RegisterPresenter : RegisterContract.Presenter {

    private lateinit var view: RegisterContract.View
    private lateinit var auth: FirebaseAuth

    override fun setView(view: RegisterContract.View) {
        this.view = view
    }

    override fun signUp(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        view.showProgress()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                view.showToast("You have been successfully registered")
                view.goToLoginActivity()
            } else run {
                if (it.exception is FirebaseAuthUserCollisionException) {
                    view.showToast("This email is already registered")
                } else {
                    view.showToast(it.exception?.message!!)
                }
            }
            view.hideProgress()
        }
    }

    override fun userRegister() {
        view.validateInfo()
    }
}

