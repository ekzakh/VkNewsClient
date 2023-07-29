package com.ekzakh.vknewsclient.navigation

import android.net.Uri
import com.ekzakh.vknewsclient.domain.FeedPost
import com.google.gson.Gson

sealed class Screen(
    val route: String,
) {
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"
        fun routeWithArgs(feedPost: FeedPost): String {
            val feedPostJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostJson.encode()}"
        }
    }

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    companion object {
        const val KEY_FEED_POST = "feed_post_id"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "newsfeed"
        const val ROUTE_FAVORITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}

fun String.encode(): String = Uri.encode(this)
