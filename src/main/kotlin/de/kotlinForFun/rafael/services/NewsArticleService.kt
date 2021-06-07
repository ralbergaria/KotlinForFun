package de.kotlinForFun.rafael.services

import de.kotlinForFun.rafael.models.NewsArticle
import de.kotlinForFun.rafael.repositories.NewsArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class NewsArticleService @Autowired constructor(val newsArticleRepository: NewsArticleRepository) {

    fun saveNewsArticle(newsArticle: NewsArticle): NewsArticle {
        return newsArticleRepository.save(newsArticle)
    }

    fun getNewsArticleById(id: Int): NewsArticle? {
        return newsArticleRepository.findById(id).orElse(null)
    }

    fun getAllNewsArticles(): MutableList<NewsArticle> {
        return newsArticleRepository.findAll().toMutableList()
    }
}