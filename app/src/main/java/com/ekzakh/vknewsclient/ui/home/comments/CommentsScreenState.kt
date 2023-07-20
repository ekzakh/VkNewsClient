package com.ekzakh.vknewsclient.ui.home.comments

import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.PostComment

sealed class CommentsScreenState {
    object Initial : CommentsScreenState()

    data class Comments(val post: FeedPost, val comments: List<PostComment>) : CommentsScreenState()
}
