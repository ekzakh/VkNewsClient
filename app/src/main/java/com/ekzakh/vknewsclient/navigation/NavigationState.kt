package com.ekzakh.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.ekzakh.vknewsclient.domain.FeedPost

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

    fun navigateToComments(feedPost: FeedPost) {
        navHostController.navigate(Screen.Comments.routeWithArgs(feedPost))
    }
}

@Composable
fun rememberNavigationState(navHostController: NavHostController): NavigationState = remember {
    NavigationState(navHostController)
}
