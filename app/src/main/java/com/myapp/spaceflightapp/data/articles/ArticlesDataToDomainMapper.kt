package com.myapp.spaceflightapp.data.articles

import com.myapp.spaceflightapp.core.Abstract

abstract class ArticlesDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<ArticleData>, T>()