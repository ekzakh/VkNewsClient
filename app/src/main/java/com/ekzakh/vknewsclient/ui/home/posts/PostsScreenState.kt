package com.ekzakh.vknewsclient.ui.home.posts

import com.ekzakh.vknewsclient.domain.FeedPost

sealed class PostsScreenState {
    object Initial : PostsScreenState()

    data class Posts(val posts: List<FeedPost>) : PostsScreenState()
}
