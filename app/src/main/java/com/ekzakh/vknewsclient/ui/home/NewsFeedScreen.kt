package com.ekzakh.vknewsclient.ui.home

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekzakh.vknewsclient.data.VkTokenStorage
import com.ekzakh.vknewsclient.data.mapper.NewsFeedMapper
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.ui.home.posts.NewsFeedScreenState
import com.ekzakh.vknewsclient.ui.home.posts.NewsFeedViewModel
import com.ekzakh.vknewsclient.ui.home.posts.NewsFeedViewModelFactory
import com.ekzakh.vknewsclient.ui.home.posts.PostCard

@Composable
fun NewsFeedPost(
    padding: PaddingValues,
    onCommentClickListener: (FeedPost) -> Unit,
    context: Context,
) {
    val viewModel: NewsFeedViewModel = viewModel(
        factory = NewsFeedViewModelFactory(
            tokenStorage = VkTokenStorage(context = context),
            mapper = NewsFeedMapper(),
        ),
    )
    val screenState = viewModel.screenState.observeAsState(NewsFeedScreenState.Initial).value
    when (screenState) {
        is NewsFeedScreenState.Posts -> {
            FeedPosts(
                posts = screenState.posts,
                viewModel = viewModel,
                padding = padding,
                onCommentClick = onCommentClickListener,
            )
        }

        is NewsFeedScreenState.Initial -> {}
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun FeedPosts(
    posts: List<FeedPost>,
    viewModel: NewsFeedViewModel,
    padding: PaddingValues,
    onCommentClick: (FeedPost) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(posts, key = { it.id }) { post ->
            val state = rememberDismissState()
            if (state.isDismissed(DismissDirection.EndToStart)) {
                viewModel.delete(post)
            }
            SwipeToDismiss(
                modifier = Modifier.animateItemPlacement(),
                state = state,
                directions = setOf(DismissDirection.EndToStart),
                background = {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(16.dp)
                            .background(MaterialTheme.colors.onSecondary),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = null,
                            modifier = Modifier.size(44.dp),
                        )
                    }
                },
            ) {
                PostCard(
                    post = post,
                    viewStatisticClickListener = viewModel::changeViewStatistic,
                    shareStatisticClickListener = viewModel::share,
                    commentStatisticClickListener = { onCommentClick(it) },
                    favoriteStatisticClickListener = viewModel::favorite,
                )
            }
        }
    }
}
