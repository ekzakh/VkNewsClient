package com.ekzakh.vknewsclient.ui.home

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
import com.ekzakh.vknewsclient.ui.MainViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    padding: PaddingValues,
) {
    val posts = viewModel.feedPost.observeAsState(listOf())
    LazyColumn(
        modifier = Modifier.padding(8.dp),
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(posts.value, key = { it.id }) { post ->
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
                    commentStatisticClickListener = viewModel::comment,
                    favoriteStatisticClickListener = viewModel::favorite,
                )
            }
        }
    }
}
