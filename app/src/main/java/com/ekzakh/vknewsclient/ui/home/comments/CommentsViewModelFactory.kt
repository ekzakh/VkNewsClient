package com.ekzakh.vknewsclient.ui.home.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekzakh.vknewsclient.domain.FeedPost
import java.lang.IllegalStateException

@Suppress("UNCHECKED_CAST")
class CommentsViewModelFactory(private val feedPost: FeedPost) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass == CommentsViewModel::class.java) {
            CommentsViewModel(feedPost) as T
        } else {
            throw IllegalStateException("ViewModel not found for $modelClass")
        }
    }
}
