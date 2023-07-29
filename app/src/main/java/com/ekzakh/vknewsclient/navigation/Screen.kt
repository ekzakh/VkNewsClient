package com.ekzakh.vknewsclient.navigation

import com.ekzakh.vknewsclient.domain.FeedPost

sealed class Screen(
    val route: String,
) {
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS) {
        private const val ROUTE_FOR_ARGS = "comments"
        fun routeWithArgs(feedPost: FeedPost): String =
            "$ROUTE_FOR_ARGS/${feedPost.id}/${feedPost.text}"
    }

    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    companion object {
        const val KEY_FEED_POST_TEXT = "feed_post_text"
        const val KEY_FEED_POST_ID = "feed_post_id"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_FEED_POST_TEXT}"
        const val ROUTE_NEWS_FEED = "newsfeed"
        const val ROUTE_FAVORITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}
