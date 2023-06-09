package com.ekzakh.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ekzakh.vknewsclient.ui.MainScreen
import com.ekzakh.vknewsclient.ui.MainViewModel
import com.ekzakh.vknewsclient.ui.theme.VkNewsClientTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme(dynamicColor = false) {
                MainScreen(viewModel)
            }
        }
    }
}
