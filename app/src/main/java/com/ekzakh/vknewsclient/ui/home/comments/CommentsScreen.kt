package com.ekzakh.vknewsclient.ui.home.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.PostComment

@Composable
fun CommentsScreen(
    onBackPressed: () -> Unit,
    feedPost: FeedPost,
    text: String,
) {
    val viewModel: CommentsViewModel = viewModel(
        factory = CommentsViewModelFactory(feedPost),
    )
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial).value
    if (screenState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Comments for FeedPost Id ${screenState.post.id} \n $text")
                    },
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                )
            },
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues = paddingValues),
                contentPadding = PaddingValues(
                    bottom = 72.dp,
                ),
            ) {
                items(
                    items = screenState.comments,
                    key = { it.id },
                ) { postComment ->
                    Comment(postComment = postComment)
                }
            }
        }
    }
}

@Composable
fun Comment(postComment: PostComment) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = postComment.authorAvatarId),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "${postComment.authorName} commentId: ${postComment.id}",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 12.sp,
            )
            Text(
                text = postComment.commentText,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = postComment.publicationDate,
                color = MaterialTheme.colors.onSecondary,
                fontSize = 12.sp,
            )
        }
    }
}

@Preview
@Composable
fun CommentPreview() {
    Comment(postComment = PostComment(1))
}
