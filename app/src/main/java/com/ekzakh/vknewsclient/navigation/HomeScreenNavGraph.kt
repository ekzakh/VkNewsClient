package com.ekzakh.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ekzakh.vknewsclient.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreen: @Composable () -> Unit,
    commentsScreen: @Composable (FeedPost, String) -> Unit,
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
                navArgument(Screen.KEY_FEED_POST_ID) {
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_FEED_POST_TEXT) {
                    type = NavType.StringType
                },
            ),
        ) {
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID)
                ?: throw IllegalStateException("Incorrect feed post id")
            val text = it.arguments?.getString(Screen.KEY_FEED_POST_TEXT) ?: ""
            commentsScreen(FeedPost(id = feedPostId), text)
        }
    }
}
