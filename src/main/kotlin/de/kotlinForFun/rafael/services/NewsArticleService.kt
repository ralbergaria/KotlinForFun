package de.kotlinForFun.rafael.services

import de.kotlinForFun.rafael.models.NewsArticle
import de.kotlinForFun.rafael.repositories.NewsArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NewsArticleService @Autowired constructor(val newsArticleRepository: NewsArticleRepository) {

    fun saveNewsArticle(newsArticle: NewsArticle) {
        newsArticleRepository.save(newsArticle)
    }

    fun getNewsArticleById(id: Int): NewsArticle? {
        return newsArticleRepository.findNewsArticleById(id)
    }

    fun getAllNewsArticles(): List<NewsArticle> {
        return newsArticleRepository.findAll()
    }
}