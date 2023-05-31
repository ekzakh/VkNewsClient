package com.ekzakh.vknewsclient

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ekzakh.vknewsclient.ui.theme.VkNewsClientTheme

@Composable
fun NewsPost() {
    Card() {
        Column(modifier = Modifier.padding(8.dp)) {
            NewsHeader(
                painterResource(R.drawable.ic_launcher_foreground),
                "deleted",
                "14:00",
            )
            Spacer(modifier = Modifier.size(8.dp))
            NewsBody(
                stringResource(id = R.string.content),
                painterResource(id = R.drawable.ic_launcher_background),
            )
            Spacer(modifier = Modifier.size(8.dp))
            NewsStatistics(
                watches = "809",
                reply = "8",
                comments = "6",
                favorites = "23",
            )
        }
    }
}

@Composable
fun NewsHeader(groupImage: Painter, title: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = groupImage,
            contentDescription = null,
            modifier = Modifier.clip(CircleShape)
                .size(50.dp),
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            val color = MaterialTheme.colorScheme.onPrimary
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = color,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = time,
                color = MaterialTheme.colorScheme.onSecondary,
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,
            )
        }
    }
}

@Composable
fun NewsBody(content: String, image: Painter, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(text = content)
        Image(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp),
            contentScale = ContentScale.FillBounds,
            painter = image,
            contentDescription = "Post image",
        )
    }
}

@Composable
fun NewsStatistics(
    watches: String,
    reply: String,
    comments: String,
    favorites: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(modifier = Modifier.weight(1F)) {
            IconWithText(painterResource(id = R.drawable.ic_eye_24), watches)
        }
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconWithText(painterResource(id = R.drawable.ic_outline_reply_24), reply)
            IconWithText(painterResource(id = R.drawable.ic_outline_comment_24), comments)
            IconWithText(painterResource(id = R.drawable.ic_favorite_border_24), favorites)
        }
    }
}

@Composable
fun IconWithText(icon: Painter, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondary,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Preview
@Composable
fun NewsPostPreviewLight() {
    VkNewsClientTheme(darkTheme = false, dynamicColor = false) {
        NewsPost()
    }
}

@Preview
@Composable
fun NewsPostPreviewDark() {
    VkNewsClientTheme(darkTheme = true, dynamicColor = false) {
        NewsPost()
    }
}
