package com.ekzakh.vknewsclient.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticItem

class MainViewModel : ViewModel() {
    private val _feedPost = MutableLiveData<FeedPost>(FeedPost())

    val feedPost: LiveData<FeedPost>
        get() = _feedPost

    fun changeStatistic(statisticItem: StatisticItem) {
        val oldStatistics = feedPost.value?.statistics ?: throw IllegalStateException()
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem == statisticItem) {
                    statisticItem.copy(value = statisticItem.value + 1)
                } else {
                    oldItem
                }
            }
        }
        _feedPost.value = _feedPost.value?.copy(statistics = newStatistics)
    }
}
