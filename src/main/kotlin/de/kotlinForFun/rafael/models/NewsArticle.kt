package de.kotlinForFun.rafael.models

import de.kotlinForFun.rafael.helpers.RelevanceHelper
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@MappedSuperclass
abstract class AbstractJpaPersistable<T : Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null

    @CreationTimestamp
    lateinit var created: Date

    @UpdateTimestamp
    lateinit var modified: Date

}

@Entity
@Table(name = "news_article")
data class NewsArticle(
    @Column(name = "text")
    var text: String,
    @Column(name = "title")
    var title: String
) : AbstractJpaPersistable<Int>() {
    fun mapToDto(): NewsArticleDTO {
        val newsArticleDTO = NewsArticleDTO(text, title)
        newsArticleDTO.id = id!!
        newsArticleDTO.relevance = RelevanceHelper.getRelevance(this)
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
    lateinit var relevance: RelevanceType
    var id: Int = 0
    fun mapToEntity(): NewsArticle {
        val newArticle = NewsArticle(text, title)
        newArticle.id = id
        return newArticle
    }
}

enum class RelevanceType {
    HOT, BORING, STANDARD
}