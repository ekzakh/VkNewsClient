package com.ekzakh.vknewsclient.ui.home.posts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ekzakh.vknewsclient.R
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticItem
import com.ekzakh.vknewsclient.domain.StatisticType
import com.ekzakh.vknewsclient.ui.theme.DarkRed

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: FeedPost,
    viewStatisticClickListener: (FeedPost) -> Unit = {},
    shareStatisticClickListener: (FeedPost) -> Unit = {},
    commentStatisticClickListener: (FeedPost) -> Unit = {},
    favoriteStatisticClickListener: (FeedPost) -> Unit = {},
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader(post)
            Spacer(modifier = Modifier.size(8.dp))
            PostBody(feedPost = post)
            Spacer(modifier = Modifier.size(8.dp))
            PostStatistics(
                statistics = post.statistics,
                viewClickListener = { viewStatisticClickListener(post) },
                shareClickListener = { shareStatisticClickListener(post) },
                commentClickListener = { commentStatisticClickListener(post) },
                favoriteClickListener = { favoriteStatisticClickListener(post) },
                isFavorite = post.isFavorite,
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
        AsyncImage(
            model = feedPost.communityImageUrl,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .wrapContentHeight(),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            val color = MaterialTheme.colors.onPrimary
            Text(
                text = feedPost.communityName,
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
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            contentScale = ContentScale.FillBounds,
            model = feedPost.contentImageUrl,
            contentDescription = "Post image",
        )
    }
}

@Composable
fun PostStatistics(
    modifier: Modifier = Modifier,
    statistics: List<StatisticItem>,
    viewClickListener: () -> Unit,
    shareClickListener: () -> Unit,
    commentClickListener: () -> Unit,
    favoriteClickListener: () -> Unit,
    isFavorite: Boolean,
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
                formatStatisticCount(viewStatistic.value),
                clickListener = { viewClickListener() },
            )
        }
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            val repostsStatistic = statistics.statisticItem(StatisticType.REPOSTS)
            IconWithText(
                painterResource(id = R.drawable.ic_outline_reply_24),
                formatStatisticCount(repostsStatistic.value),
                clickListener = { shareClickListener() },
            )
            val commentStatistic = statistics.statisticItem(StatisticType.COMMENT)
            IconWithText(
                painterResource(id = R.drawable.ic_outline_comment_24),
                formatStatisticCount(commentStatistic.value),
                clickListener = { commentClickListener() },
            )
            val likesStatistic = statistics.statisticItem(StatisticType.LIKES)
            IconWithText(
                painterResource(
                    id = if (isFavorite) {
                        R.drawable.ic_favorite_set
                    } else {
                        R.drawable.ic_favorite_border_24
                    },
                ),
                formatStatisticCount(likesStatistic.value),
                clickListener = { favoriteClickListener() },
                tint = if (isFavorite) DarkRed else MaterialTheme.colors.onSecondary,
            )
        }
    }
}

private fun formatStatisticCount(count: Int): String {
    return if (count > 100_000) {
        String.format("%sK", count / 1000)
    } else if (count > 1000) {
        String.format("%.1fK", count / 1000F)
    } else {
        count.toString()
    }
}

private fun List<StatisticItem>.statisticItem(type: StatisticType) =
    this.find { it.type == type } ?: throw IllegalStateException("Not found value for $type")

@Composable
fun IconWithText(
    icon: Painter,
    value: String,
    clickListener: () -> Unit,
    tint: Color = MaterialTheme.colors.onSecondary,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            clickListener.invoke()
        },
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            painter = icon,
            contentDescription = null,
            tint = tint,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            color = MaterialTheme.colors.onSecondary,
        )
    }
}
