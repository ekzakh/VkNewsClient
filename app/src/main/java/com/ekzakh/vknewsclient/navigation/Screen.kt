package com.ekzakh.vknewsclient.navigation

sealed class Screen(
    val route: String,
) {
    object Home : Screen(ROUTE_HOME)
    object Comments : Screen(ROUTE_COMMENTS)
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments"
        const val ROUTE_NEWS_FEED = "newsfeed"
        const val ROUTE_FAVORITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}
