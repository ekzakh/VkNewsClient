package com.ekzakh.vknewsclient.ui.home.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekzakh.vknewsclient.data.TokenStorage
import com.ekzakh.vknewsclient.data.mapper.Mapper
import com.ekzakh.vknewsclient.data.model.NewsFeedResponseDto
import com.ekzakh.vknewsclient.data.network.ApiFactory
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticType
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch

class NewsFeedViewModel(
    private val tokenStorage: TokenStorage.Read<VKAccessToken?>,
    private val mapper: Mapper<NewsFeedResponseDto, List<FeedPost>>,
) : ViewModel() {

    private val _screenState = MutableLiveData<NewsFeedScreenState>(NewsFeedScreenState.Initial)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        val token = tokenStorage.read() ?: return
        viewModelScope.launch {
            val response = ApiFactory.apiService.fetchRecommendation(token.accessToken)
            _screenState.value = NewsFeedScreenState.Posts(mapper.map(response))
        }
    }

    fun changeViewStatistic(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.VIEW)
    }

    fun share(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.REPOSTS)
    }

    fun favorite(feedPost: FeedPost) {
        changeStatistic(feedPost, StatisticType.LIKES)
    }

    private fun changeStatistic(feedPost: FeedPost, statisticType: StatisticType) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
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
        _screenState.value = NewsFeedScreenState.Posts(changed)
    }

    fun delete(feedPost: FeedPost) {
        val currentState = _screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        currentState.posts.toMutableList().apply {
            remove(feedPost)
        }
    }
}
