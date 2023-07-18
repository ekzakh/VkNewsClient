package com.ekzakh.vknewsclient.domain

import com.ekzakh.vknewsclient.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.ic_eye_24,
    val commentText: String = "Comment",
    val publicationDate: String = "14:00",
)
