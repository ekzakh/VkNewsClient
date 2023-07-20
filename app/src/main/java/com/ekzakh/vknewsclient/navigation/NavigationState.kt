package com.ekzakh.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

class NavigationState(val navHostController: NavHostController) {
    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToComments() {
        navHostController.navigate(Screen.Comments.route)
    }
}

@Composable
fun rememberNavigationState(navHostController: NavHostController): NavigationState = remember {
    NavigationState(navHostController)
}
