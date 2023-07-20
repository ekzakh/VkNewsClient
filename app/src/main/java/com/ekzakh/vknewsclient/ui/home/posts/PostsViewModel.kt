package com.ekzakh.vknewsclient.ui.home.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticType

class PostsViewModel : ViewModel() {
    private val _initialPosts = mutableListOf<FeedPost>().apply {
        repeat(20) {
            add(FeedPost(id = it))
        }
    }

    private val _screenState =
        MutableLiveData<PostsScreenState>(PostsScreenState.Posts(_initialPosts))
    val screenState: LiveData<PostsScreenState> = _screenState

    fun changeViewStatistic(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.VIEW)
    }

    fun share(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.SHARE)
    }

    fun favorite(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.FAVORITE)
    }

    private fun changeStatistic(feedPost: FeedPost, statisticType: StatisticType) {
        val currentState = _screenState.value
        if (currentState !is PostsScreenState.Posts) return
        val changed = currentState.posts.toMutableList()
        changed.replaceAll { itemPost ->
            if (itemPost == feedPost) {
                val newStatistics = feedPost.statistics.toMutableList().apply {
                    replaceAll { oldItem ->
                        if (oldItem.type == statisticType) {
                            oldItem.copy(value = oldItem.value + 1)
                        } else {
                            oldItem
                        }
                    }
                }
                itemPost.copy(statistics = newStatistics)
            } else {
                itemPost
            }
        }
        _screenState.value = PostsScreenState.Posts(changed)
    }

    fun delete(feedPost: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is PostsScreenState.Posts) return
        currentState.posts.toMutableList().apply {
            remove(feedPost)
        }
    }
}
