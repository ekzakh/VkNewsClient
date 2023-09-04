package com.ekzakh.vknewsclient.data.mapper

import com.ekzakh.vknewsclient.data.model.NewsFeedResponseDto
import com.ekzakh.vknewsclient.domain.FeedPost
import com.ekzakh.vknewsclient.domain.StatisticItem
import com.ekzakh.vknewsclient.domain.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper : Mapper<NewsFeedResponseDto, List<FeedPost>> {
    override fun map(source: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()
        val posts = source.newsFeedContent.posts
        val groups = source.newsFeedContent.groups

        for (post in posts) {
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            result.add(
                FeedPost(
                    id = post.id,
                    communityName = group.name,
                    date = post.date.toString(),
                    communityImageUrl = group.imageUrl,
                    text = post.text,
                    contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url
                        ?: "",
                    statistics = listOf(
                        StatisticItem(StatisticType.LIKES, post.likes.count),
                        StatisticItem(StatisticType.VIEW, post.views.count),
                        StatisticItem(StatisticType.COMMENT, post.comments.count),
                        StatisticItem(StatisticType.REPOSTS, post.comments.count),
                    ),
                ),
            )
        }
        return result
    }
}
