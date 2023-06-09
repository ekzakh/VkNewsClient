package com.ekzakh.vknewsclient.navigation

sealed class Screen(
    val route: String,
) {
    object NewsFeed : Screen(ROUTE_NEWS_FEED)
    object Favorite : Screen(ROUTE_FAVORITE)
    object Profile : Screen(ROUTE_PROFILE)

    private companion object {
        const val ROUTE_NEWS_FEED = "newsfeed"
        const val ROUTE_FAVORITE = "favourite"
        const val ROUTE_PROFILE = "profile"
    }
}
