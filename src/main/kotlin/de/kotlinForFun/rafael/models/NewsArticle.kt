package de.kotlinForFun.rafael.models

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class NewsArticle(val text: String, val title: String) {
    var id: Int = 0
    lateinit var creationDate : LocalDateTime
    fun mapToDto(): NewsArticleDTO {
        val newsArticleDTO = NewsArticleDTO(text, title)
        newsArticleDTO.id = id
        return newsArticleDTO
    }
}

data class NewsArticleDTO
    (
    @field:NotEmpty(message = "The news article text can not be empty")
    @field:NotNull(message = "The news article text can not be empty")
    val text: String,
    @field:NotEmpty(message = "The news article title can not be empty")
    @field:NotNull(message = "The news article title can not be empty")
    val title: String
) {
    var id: Int = 0
    fun mapToEntity(): NewsArticle {
        val newArticle = NewsArticle(text, title)
        newArticle.id = id
        return newArticle
    }
}