package com.ekzakh.vknewsclient.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StatisticItem(
    val type: StatisticType,
    val value: Int,
) : Parcelable

enum class StatisticType {
    VIEW, REPOSTS, COMMENT, LIKES
}
