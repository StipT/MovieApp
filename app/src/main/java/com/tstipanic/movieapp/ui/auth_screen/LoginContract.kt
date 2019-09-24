package com.tstipanic.movieapp.ui.auth_screen

interface LoginContract {

    interface View {
        fun goToRegisterActivity()

        fun goToMainActivity()

        fun showToast(message: String)

        fun validateInfo()

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {

        fun setView(view: View)

        fun signIn(email: String, password: String)

        fun userLogin()
    }
}