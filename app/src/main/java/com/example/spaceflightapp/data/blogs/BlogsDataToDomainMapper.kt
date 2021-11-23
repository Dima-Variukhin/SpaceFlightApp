package com.example.spaceflightapp.data.blogs

import com.example.spaceflightapp.core.Abstract

abstract class BlogsDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<List<BlogData>, T>()