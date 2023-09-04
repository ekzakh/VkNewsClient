package com.ekzakh.vknewsclient.ui.home.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ekzakh.vknewsclient.data.TokenStorage
import com.ekzakh.vknewsclient.data.mapper.Mapper
import com.ekzakh.vknewsclient.data.model.NewsFeedResponseDto
import com.ekzakh.vknewsclient.domain.FeedPost
import com.vk.api.sdk.auth.VKAccessToken
import java.lang.IllegalStateException

@Suppress("UNCHECKED_CAST")
class NewsFeedViewModelFactory(
    private val tokenStorage: TokenStorage.Read<VKAccessToken?>,
    private val mapper: Mapper<NewsFeedResponseDto, List<FeedPost>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        if (modelClass == NewsFeedViewModel::class.java) {
            NewsFeedViewModel(tokenStorage, mapper) as T
        } else {
            throw IllegalStateException("ViewModel not found for $modelClass")
        }
}
