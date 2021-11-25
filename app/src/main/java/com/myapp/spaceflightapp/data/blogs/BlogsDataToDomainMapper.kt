package com.myapp.spaceflightapp.data.blogs

import com.myapp.spaceflightapp.core.Abstract

abstract class BlogsDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<BlogData>, T>()