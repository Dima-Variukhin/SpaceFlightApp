package com.myapp.spaceflightapp.data.articles.cache

import com.myapp.spaceflightapp.data.articles.ArticleData
import com.myapp.spaceflightapp.data.articles.ToArticleMapper
import org.junit.Assert.*
import org.junit.Test

class ArticleCacheMapperTest {

    @Test
    fun test_mapping() {
        val mapper = ArticleCacheMapper.Base(TestMapper())
        val actual = mapper.map(listOf(
            ArticleDb().apply {
                idA = 1
                titleA = "one"
                urlA = "urlOne"
                imageUrlA = "imageOne"
                newsSiteA = "newsOne"
                summaryA = "summaryOne"
                publishedAtA = "publishedOne"
                updatedAtA = "updatedOne"
            },
            ArticleDb().apply {
                idA = 2
                titleA = "two"
                urlA = "urlTwo"
                imageUrlA = "imageTwo"
                newsSiteA = "newsTwo"
                summaryA = "summaryTwo"
                publishedAtA = "publishedTwo"
                updatedAtA = "updatedTwo"
            },
            ArticleDb().apply {
                idA = 3
                titleA = "three"
                urlA = "urlThree"
                imageUrlA = "imageThree"
                newsSiteA = "newsThree"
                summaryA = "summaryThree"
                publishedAtA = "publishedThree"
                updatedAtA = "updatedThree"
            }
        ))
        val expected = listOf(
            ArticleData.Base(
                1, "one",
                "urlOne",
                "imageOne",
                "newsOne",
                "summaryOne",
                "publishedOne",
                "updatedOne",
            ),
            ArticleData.Base(
                2, "two",
                "urlTwo",
                "imageTwo",
                "newsTwo",
                "summaryTwo",
                "publishedTwo",
                "updatedTwo",
            ),
            ArticleData.Base(
                3, "three",
                "urlThree",
                "imageThree",
                "newsThree",
                "summaryThree",
                "publishedThree",
                "updatedThree",
            )
        )
        assertEquals(expected, actual)
    }

    private inner class TestMapper : ToArticleMapper<ArticleData> {
        override fun map(
            idA: Int,
            titleA: String,
            urlA: String,
            imageUrlA: String,
            newsSiteA: String,
            summaryA: String,
            publishedAtA: String,
            updatedAtA: String
        ) = ArticleData.Base(
            idA,
            titleA,
            urlA,
            imageUrlA,
            newsSiteA,
            summaryA,
            publishedAtA,
            updatedAtA
        )
    }
}