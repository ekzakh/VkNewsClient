package com.ekzakh.vknewsclient.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ekzakh.vknewsclient.domain.FeedPost

@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.padding(8.dp),
        bottomBar = {
            BottomAppBar() {
                val items =
                    listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
                val selectedIndex = remember {
                    mutableStateOf(0)
                }
                items.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = index == selectedIndex.value,
                        onClick = { selectedIndex.value = index },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = stringResource(
                                    id = navigationItem.titleResId,
                                ),
                            )
                        },
                        label = {
                            Text(stringResource(id = navigationItem.titleResId))
                        },
                    )
                }
            }
        },
    ) {
        val feedPost = remember {
            mutableStateOf(FeedPost())
        }
        NewsPost(
            modifier = Modifier.padding(it),
            feedPost = feedPost.value,
            statisticClickListener = { statisticItem ->
                val oldStatistics = feedPost.value.statistics
                val newStatistics = oldStatistics.toMutableList().apply {
                    replaceAll { oldItem ->
                        if (oldItem == statisticItem) {
                            statisticItem.copy(value = statisticItem.value + 1)
                        } else {
                            oldItem
                        }
                    }
                }
                feedPost.value = feedPost.value.copy(statistics = newStatistics)
            },
        )
    }
}
