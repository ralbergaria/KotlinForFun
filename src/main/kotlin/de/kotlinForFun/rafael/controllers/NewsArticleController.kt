package de.kotlinForFun.rafael.controllers

import de.kotlinForFun.rafael.controllers.exceptions.BadRequestException
import de.kotlinForFun.rafael.controllers.exceptions.InternalServerException
import de.kotlinForFun.rafael.controllers.exceptions.NotFoundException
import de.kotlinForFun.rafael.models.NewsArticleDTO
import de.kotlinForFun.rafael.services.NewsArticleService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/news_article")
@Api(tags = ["NewsArticle"])
class NewsArticleController @Autowired constructor(val newsArticleService: NewsArticleService) {
    companion object {
        val log: Logger = LoggerFactory.getLogger(NewsArticleController::class.java)
    }

    @GetMapping(value = ["/{newsArticleId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Get newsArticle by id")
    @ApiResponses(
        ApiResponse(code = 400, message = "Bad Request"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 500, message = "Internal Server Error")
    )
    fun getNewsArticleById(@PathVariable newsArticleId: String): NewsArticleDTO {
        log.info("GET /api/v1/news_article/$newsArticleId")
        try {
            val newsArticle = newsArticleService.getNewsArticleById(newsArticleId.toInt())
            return newsArticle?.mapToDto() ?: throw NotFoundException("Article not found!")
        } catch (e: NumberFormatException) {
            throw e.message?.let { BadRequestException(it) }!!
        } catch (e: NotFoundException) {
            throw e
        } catch (e: Exception) {
            throw e.message?.let { InternalServerException(it) }!!
        }
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "Get all newsArticles")
    @ApiResponses(
        ApiResponse(code = 500, message = "Internal Server Error"),
        ApiResponse(code = 204, message = "No content")
    )
    fun getAllNewsArticle(): ResponseEntity<List<NewsArticleDTO>> {
        log.info("GET /api/v1/news_article/")
        try {
            val newsArticlesList = newsArticleService.getAllNewsArticles()
            return if (newsArticlesList.isNullOrEmpty()) {
                ResponseEntity(HttpStatus.NO_CONTENT)
            } else {
                ResponseEntity(newsArticlesList.map { it.mapToDto() }, HttpStatus.OK)
            }
        } catch (e: Exception) {
            throw e.message?.let { InternalServerException(it) }!!
        }
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "create a newsArticles")
    @ApiResponses(
        ApiResponse(code = 400, message = "Bad Request"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 500, message = "Internal Server Error")
    )
    fun createNewsArticle(@Valid @RequestBody newsArticleDTO: NewsArticleDTO): ResponseEntity<String> {
        log.info("POST /api/v1/news_article/")
        try {
            if (newsArticleDTO.id != 0) {
                throw BadRequestException("id should be not filled!")
            }
            newsArticleDTO.id = 0
            newsArticleService.saveNewsArticle(newsArticleDTO.mapToEntity())
            return ResponseEntity("NewsArticle created with success!", HttpStatus.ACCEPTED)
        } catch (e: BadRequestException) {
            throw e
        } catch (e: Exception) {
            throw e.message?.let { InternalServerException(it) }!!
        }
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ApiOperation(value = "update a newsArticles")
    @ApiResponses(
        ApiResponse(code = 400, message = "Bad Request"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 500, message = "Internal Server Error")
    )
    fun updateNewsArticle(@Valid @RequestBody newsArticleDTO: NewsArticleDTO): ResponseEntity<*> {
        log.info("PUT /api/v1/news_article/")
        try {
            if (newsArticleDTO.id == 0) {
                throw BadRequestException("Missing newsArticle id!")
            }
            if (newsArticleService.getNewsArticleById(newsArticleDTO.id) == null) {
                throw NotFoundException("NewsArticle not found!")
            }
            newsArticleService.saveNewsArticle(newsArticleDTO.mapToEntity())
            return ResponseEntity.ok("newsArticle update with success!")
        } catch (e: NotFoundException) {
            throw e
        } catch (e: BadRequestException) {
            throw e
        } catch (e: Exception) {
            throw e.message?.let { InternalServerException(it) }!!
        }
    }
}