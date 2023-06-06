package com.ekzakh.vknewsclient.domain

data class StatisticItem(
    val type: StatisticType,
    val value: Int,
)

enum class StatisticType {
    VIEW, SHARE, COMMENT, FAVORITE
}
