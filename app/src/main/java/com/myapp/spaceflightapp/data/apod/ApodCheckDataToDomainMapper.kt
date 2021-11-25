package com.myapp.spaceflightapp.data.apod

import com.myapp.spaceflightapp.core.Abstract

abstract class ApodCheckDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<ApodData, T>()