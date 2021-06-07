package de.kotlinForFun.rafael.repositories

import de.kotlinForFun.rafael.models.NewsArticle
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Repository
interface NewsArticleRepository : CrudRepository<NewsArticle, Int>