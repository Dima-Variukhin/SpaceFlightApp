package com.myapp.spaceflightapp.data.articles.cache

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.articles.ArticleData
import com.myapp.spaceflightapp.data.articles.ToArticleMapper

interface ArticleCacheMapper : Abstract.Mapper.Data<List<ArticleDb>, List<ArticleData>> {
    class Base(private val mapper: ToArticleMapper<ArticleData>) : ArticleCacheMapper {
        override fun map(data: List<ArticleDb>) = data.map { articleDb -> articleDb.map(mapper) }
    }
}