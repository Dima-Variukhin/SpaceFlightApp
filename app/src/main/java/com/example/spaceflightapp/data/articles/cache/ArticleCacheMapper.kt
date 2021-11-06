package com.example.spaceflightapp.data.articles.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.articles.ArticleData
import com.example.spaceflightapp.data.articles.ToArticleMapper

interface ArticleCacheMapper : Abstract.Mapper.Data<List<ArticleDb>, List<ArticleData>> {
    class Base(private val mapper: ToArticleMapper<ArticleData>) : ArticleCacheMapper {
        override fun map(data: List<ArticleDb>) = data.map { articleDb -> articleDb.map(mapper) }
    }
}