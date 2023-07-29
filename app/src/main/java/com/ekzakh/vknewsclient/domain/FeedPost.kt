package com.ekzakh.vknewsclient.domain

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.ekzakh.vknewsclient.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable {

    companion object {
        val NavigationType = object : NavType<FeedPost>(false) {
            override fun get(bundle: Bundle, key: String): FeedPost? {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable(key, FeedPost::class.java)
                } else {
                    bundle.getParcelable(key)
                }
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }
        }
    }
}
