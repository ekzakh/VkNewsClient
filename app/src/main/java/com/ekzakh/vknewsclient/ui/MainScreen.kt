package com.ekzakh.vknewsclient.ui

import androidx.compose.foundation.background
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.navigation.AppNavGraph
import com.ekzakh.vknewsclient.navigation.rememberNavigationState
import com.ekzakh.vknewsclient.ui.favorite.FavoriteScreen
import com.ekzakh.vknewsclient.ui.home.HomeScreen
import com.ekzakh.vknewsclient.ui.home.comments.CommentsScreen
import com.ekzakh.vknewsclient.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState(rememberNavController())
    val commentsInPost: MutableState<FeedPost?> = remember {
        mutableStateOf(null)
    }
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.background),
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile,
                )
                items.forEach { item ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == item.screen.route
                    } ?: false
                    BottomNavigationItem(
                        selected = selected,
                        onClick = {
                            if (!selected) {
                                navigationState.navigateTo(item.screen.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(
                                    id = item.titleResId,
                                ),
                            )
                        },
                        label = {
                            Text(stringResource(id = item.titleResId))
                        },
                    )
                }
            }
        },
    ) { padding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(
                    padding = padding,
                    onCommentClickListener = { feedPost ->
                        commentsInPost.value = feedPost
                        navigationState.navigateToComments()
                    },
                )
            },
            commentsScreenContent = {
                CommentsScreen(
                    onBackPressed = { navigationState.navHostController.popBackStack() },
                    feedPost = commentsInPost.value!!,
                )
            },
            favoriteScreenContent = { FavoriteScreen() },
            profileScreenContent = { ProfileScreen() },
        )
    }
}
