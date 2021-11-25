package com.myapp.spaceflightapp.data.articles.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.articles.ArticleData
import com.myapp.spaceflightapp.data.articles.ToArticleMapper

interface ArticleCloudMapper : Abstract.Mapper.Data<List<ArticleCloud>, List<ArticleData>> {
    class Base(private val articleMapper: ToArticleMapper<ArticleData>) : ArticleCloudMapper {
        override fun map(data: List<ArticleCloud>) =
            data.map { articleData ->
                articleData.map(articleMapper)
            }
    }
}