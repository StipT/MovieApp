package com.tstipanic.movieapp.ui.auth_screen

interface RegisterContract {

    interface View {
        fun goToLoginActivity()

        fun validateInfo()

        fun showToast(message: String)

        fun showProgress()

        fun hideProgress()

    }

    interface Presenter {
        fun setView(view: RegisterContract.View)

        fun signUp(email: String, password: String)

        fun userRegister()

    }
}