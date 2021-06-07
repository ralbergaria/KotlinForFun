package de.kotlinForFun.rafael.helpers

import de.kotlinForFun.rafael.models.NewsArticle
import de.kotlinForFun.rafael.models.RelevanceType
import org.apache.commons.lang3.StringUtils
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.function.Predicate


class RelevanceHelper {
    companion object {
        fun getRelevance(newsArticle: NewsArticle): RelevanceType {
            return newsArticleRelevance.entries.stream().filter { mapping -> mapping.value.test(newsArticle) }.map { it.key }.findFirst().get()
        }

        private val newsArticleRelevance: Map<RelevanceType, Predicate<NewsArticle>> = object : HashMap<RelevanceType, Predicate<NewsArticle>>() {
            init {
                put(RelevanceType.HOT, hotType())
                put(RelevanceType.BORING, boringType())
                put(RelevanceType.STANDARD, standardType())
            }
        }

        private fun standardType(): Predicate<NewsArticle> {
            return Predicate<NewsArticle> { nA: NewsArticle -> !hotType().test(nA) && !boringType().test(nA) }
        }

        private fun hotType(): Predicate<NewsArticle> {
            return Predicate<NewsArticle> { nA: NewsArticle -> isLeastByMinutesAgo(1, nA.created) && hasMoreExplanationMarksThanStops(nA.text) }
        }

        private fun boringType(): Predicate<NewsArticle> {
            return Predicate<NewsArticle> { nA: NewsArticle -> isLeastByMinutesAgo(5, nA.created) && hasMoreCommasThanStops(nA.text) }
        }

        private fun hasMoreCommasThanStops(text: String): Boolean {
            val countCommas: Int = StringUtils.countMatches(text, ",")
            val countStops: Int = StringUtils.countMatches(text, ".")
            return countCommas > countStops
        }

        private fun isLeastByMinutesAgo(minutes: Int, previous: Date): Boolean {
            val maxDuration = TimeUnit.MILLISECONDS.convert(minutes.toLong(), TimeUnit.MINUTES)
            val duration = Date().time - previous.time
            return duration <= maxDuration
        }

        private fun hasMoreExplanationMarksThanStops(text: String): Boolean {
            val countExplanationMarks: Int = StringUtils.countMatches(text, "!")
            val countStops: Int = StringUtils.countMatches(text, ".")
            return countExplanationMarks > countStops
        }
    }
}