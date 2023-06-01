package com.ekzakh.vknewsclient.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.ekzakh.vknewsclient.R

sealed class NavigationItem(
    val icon: ImageVector,
    val titleResId: Int,
) {
    object Home : NavigationItem(Icons.Outlined.Home, R.string.home)
    object Favorite : NavigationItem(Icons.Outlined.Favorite, R.string.favorite)
    object Profile : NavigationItem(Icons.Outlined.AccountCircle, R.string.profile)
}
