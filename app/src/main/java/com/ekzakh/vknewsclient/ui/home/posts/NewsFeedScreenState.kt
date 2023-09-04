package com.ekzakh.vknewsclient.ui.home.posts

import com.ekzakh.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {
    object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}
