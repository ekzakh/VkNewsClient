package com.ekzakh.vknewsclient.ui.home.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.PostComment

class CommentsViewModel(feedPost: FeedPost) : ViewModel() {

    private val comments = mutableListOf<PostComment>().apply {
        repeat(10) {
            add(PostComment(id = it))
        }
    }

    private val _screenState =
        MutableLiveData<CommentsScreenState>(CommentsScreenState.Comments(feedPost, comments))
    val screenState: LiveData<CommentsScreenState> = _screenState
}
