package com.ekzakh.vknewsclient.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekzakh.vknewsclient.R
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticItem
import com.ekzakh.vknewsclient.domain.StatisticType
import com.ekzakh.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
) {
    val feedPost = viewModel.feedPost.observeAsState().value ?: throw IllegalStateException()
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader(feedPost)
            Spacer(modifier = Modifier.size(8.dp))
            PostBody(feedPost = feedPost)
            Spacer(modifier = Modifier.size(8.dp))
            PostStatistics(
                statistics = feedPost.statistics,
                viewClickListener = viewModel::changeStatistic,
                shareClickListener = viewModel::changeStatistic,
                commentClickListener = viewModel::changeStatistic,
                favoriteClickListener = viewModel::changeStatistic,
            )
        }
    }
}

@Composable
fun PostHeader(feedPost: FeedPost) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = feedPost.avatarResId),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            val color = MaterialTheme.colors.onPrimary
            Text(
                text = feedPost.title,
                fontWeight = FontWeight.Bold,
                color = color,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = feedPost.date,
                color = MaterialTheme.colors.onSecondary,
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colors.onSecondary,
            )
        }
    }
}

@Composable
fun PostBody(modifier: Modifier = Modifier, feedPost: FeedPost) {
    Column(
        modifier = modifier,
    ) {
        Text(text = feedPost.text)
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = feedPost.imageResId),
            contentDescription = "Post image",
        )
    }
}

@Composable
fun PostStatistics(
    modifier: Modifier = Modifier,
    statistics: List<StatisticItem>,
    viewClickListener: (StatisticItem) -> Unit,
    shareClickListener: (StatisticItem) -> Unit,
    commentClickListener: (StatisticItem) -> Unit,
    favoriteClickListener: (StatisticItem) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val viewStatistic = statistics.statisticItem(StatisticType.VIEW)
        Row(
            modifier = Modifier.weight(1F),
        ) {
            IconWithText(
                painterResource(id = R.drawable.ic_eye_24),
                viewStatistic.value.toString(),
                clickListener = { viewClickListener(viewStatistic) },
            )
        }
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val shareStatistic = statistics.statisticItem(StatisticType.SHARE)
            IconWithText(
                painterResource(id = R.drawable.ic_outline_reply_24),
                shareStatistic.value.toString(),
                clickListener = { shareClickListener(shareStatistic) },
            )
            val commentStatistic = statistics.statisticItem(StatisticType.COMMENT)
            IconWithText(
                painterResource(id = R.drawable.ic_outline_comment_24),
                commentStatistic.value.toString(),
                clickListener = { commentClickListener(commentStatistic) },
            )
            val favoriteStatistic = statistics.statisticItem(StatisticType.FAVORITE)
            IconWithText(
                painterResource(id = R.drawable.ic_favorite_border_24),
                favoriteStatistic.value.toString(),
                clickListener = { favoriteClickListener(favoriteStatistic) },
            )
        }
    }
}

private fun List<StatisticItem>.statisticItem(type: StatisticType) =
    this.find { it.type == type } ?: throw IllegalStateException("Not found value for $type")

@Composable
fun IconWithText(icon: Painter, value: String, clickListener: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            clickListener.invoke()
        },
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}

@Preview
@Composable
fun NewsPostPreviewLight() {
    VkNewsClientTheme(darkTheme = false, dynamicColor = false) {
        PostCard(viewModel = MainViewModel())
    }
}

@Preview
@Composable
fun NewsPostPreviewDark() {
    VkNewsClientTheme(darkTheme = true, dynamicColor = false) {
        PostCard(viewModel = MainViewModel())
    }
}
