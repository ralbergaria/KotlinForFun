package de.kotlinForFun.rafael.repositories

import de.kotlinForFun.rafael.models.NewsArticle
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class NewsArticleRepository {
    var memoryMock: HashMap<Int, NewsArticle> = HashMap()

    fun save(newsArticle: NewsArticle) {
        if (newsArticle.id < 1) {
            newsArticle.id = memoryMock.size + 1
            newsArticle.creationDate = LocalDateTime.now()
        }
        memoryMock[newsArticle.id] = newsArticle
    }

    fun findNewsArticleById(id: Int): NewsArticle? {
        return memoryMock[id]
    }

    fun findAll(): List<NewsArticle> {
        return Collections.unmodifiableList(memoryMock.values.toList())
    }
}