package com.ekzakh.vknewsclient.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekzakh.vknewsclient.data.TokenStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel(private val tokenStorage: TokenStorage.Read<VKAccessToken?>) : ViewModel() {
    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> get() = _authState

    init {
        val token = tokenStorage.read()
        _authState.value = if (token != null && token.isValid) AuthState.Authorized else AuthState.NotAuthorized
        Log.d("TAG", "Token: ${token?.accessToken}")
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        if (result is VKAuthenticationResult.Success) {
            _authState.value = AuthState.Authorized
        } else {
            _authState.value = AuthState.NotAuthorized
        }
    }
}
