package com.ekzakh.vknewsclient.ui.main

sealed class AuthState {
    object Authorized : AuthState()
    object NotAuthorized : AuthState()
    object Initial : AuthState()
}
