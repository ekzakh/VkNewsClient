package com.ekzakh.vknewsclient.navigation

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ekzakh.vknewsclient.domain.FeedPost
import java.lang.RuntimeException

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreen: @Composable () -> Unit,
    commentsScreen: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route,
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreen()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST) {
                    type = FeedPost.NavigationType
                },
            ),
        ) {
            val feedPost = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.arguments?.getParcelable(Screen.KEY_FEED_POST, FeedPost::class.java)
            } else {
                it.arguments?.getParcelable(Screen.KEY_FEED_POST) as? FeedPost
            } ?: throw RuntimeException("Args are null")
            commentsScreen(feedPost)
        }
    }
}
