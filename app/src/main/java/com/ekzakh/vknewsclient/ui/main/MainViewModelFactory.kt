package com.ekzakh.vknewsclient.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekzakh.vknewsclient.data.TokenStorage
import com.vk.api.sdk.auth.VKAccessToken
import java.lang.IllegalStateException

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val tokenStorage: TokenStorage.Read<VKAccessToken?>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass == MainViewModel::class.java) {
            MainViewModel(tokenStorage) as T
        } else {
            throw IllegalStateException("ViewModel not found for $modelClass")
        }
}
