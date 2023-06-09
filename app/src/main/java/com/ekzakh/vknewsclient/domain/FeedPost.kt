package com.ekzakh.vknewsclient.domain

import com.ekzakh.vknewsclient.R

data class FeedPost(
    val id: Int = 0,
    val title: String = "deleted",
    val date: String = "14:00",
    val avatarResId: Int = R.drawable.ic_launcher_foreground,
    val text: String = "Давно выяснено, что при оценке дизайна и композиции читаемый текст мешает сосредоточиться.",
    val imageResId: Int = R.drawable.ic_launcher_background,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEW, 809),
        StatisticItem(StatisticType.SHARE, 8),
        StatisticItem(StatisticType.COMMENT, 6),
        StatisticItem(StatisticType.FAVORITE, 23),
    ),
)
