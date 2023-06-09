package com.ekzakh.vknewsclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticType

class MainViewModel : ViewModel() {
    private val _initialPosts = mutableListOf<FeedPost>().apply {
        repeat(20) {
            add(FeedPost(id = it))
        }
    }
    private val _feedPosts = MutableLiveData<List<FeedPost>>(_initialPosts)
    val feedPost: LiveData<List<FeedPost>> = _feedPosts

    fun changeViewStatistic(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.VIEW)
    }

    fun share(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.SHARE)
    }

    fun comment(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.COMMENT)
    }

    fun favorite(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.FAVORITE)
    }

    private fun changeStatistic(feedPost: FeedPost, statisticType: StatisticType) {
        val changed = _feedPosts.value?.toMutableList() ?: mutableListOf()
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
        _feedPosts.value = changed
    }

    fun delete(feedPost: FeedPost) {
        _feedPosts.value = _feedPosts.value?.toMutableList()?.apply {
            remove(feedPost)
        }
    }
}
