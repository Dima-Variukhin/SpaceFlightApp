package com.example.spaceflightapp.data.articles

import com.example.spaceflightapp.core.Abstract

abstract class ArticlesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<ArticleData>, T>()