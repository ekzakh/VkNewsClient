package com.ekzakh.vknewsclient.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ekzakh.vknewsclient.R
import com.ekzakh.vknewsclient.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val icon: ImageVector,
    val titleResId: Int,
) {
    object Home : NavigationItem(Screen.Home, Icons.Outlined.Home, R.string.home)
    object Favorite : NavigationItem(Screen.Favorite, Icons.Outlined.Favorite, R.string.favorite)
    object Profile : NavigationItem(Screen.Profile, Icons.Outlined.AccountCircle, R.string.profile)
}
