package com.example.spaceflightapp.data.articles.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.articles.ArticleData
import com.example.spaceflightapp.data.articles.ToArticleMapper


interface ArticleCloudMapper : Abstract.Mapper.Data<List<ArticleCloud>, List<ArticleData>> {
    class Base(private val articleMapper: ToArticleMapper<ArticleData>) : ArticleCloudMapper {
        override fun map(data: List<ArticleCloud>) =
            data.map { articleData ->
                articleData.map(articleMapper)
            }
    }
}