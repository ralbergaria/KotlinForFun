package de.kotlinForFun.rafael

import de.kotlinForFun.rafael.helpers.RelevanceHelper
import de.kotlinForFun.rafael.models.NewsArticle
import de.kotlinForFun.rafael.models.RelevanceType
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class RelevanceHelperTest {
    @Test
    fun `Should check if newsArticle is HOT`() {
        val text = "!!!rafael.."
        val newsArticle = NewsArticle(text, "testTitle")
        newsArticle.created = Date.from(LocalDateTime.now().minusSeconds(30).atZone(ZoneId.systemDefault()).toInstant())
        val relevance = RelevanceHelper.getRelevance(newsArticle)
        assertThat(relevance).isEqualTo(RelevanceType.HOT)
    }

    @Test
    fun `Should check if newsArticle is BORING`() {
        val text = ",,,rafael.."
        val newsArticle = NewsArticle(text, "testTitle")
        newsArticle.created = Date.from(LocalDateTime.now().minusMinutes(4).atZone(ZoneId.systemDefault()).toInstant())
        val relevance = RelevanceHelper.getRelevance(newsArticle)
        assertThat(relevance).isEqualTo(RelevanceType.BORING)
    }

    @Test
    fun `Should check if newsArticle is STANDARD`() {
        val text = ",,,rafael.."
        val newsArticle = NewsArticle(text, "testTitle")
        newsArticle.created = Date.from(LocalDateTime.now().minusMinutes(6).atZone(ZoneId.systemDefault()).toInstant())
        val relevance = RelevanceHelper.getRelevance(newsArticle)
        assertThat(relevance).isEqualTo(RelevanceType.STANDARD)
    }
}