package com.example.spaceflightapp.data.apod

import com.example.spaceflightapp.core.Abstract

abstract class ApodCheckDataToDomainMapper<T> :
    Abstract.Mapper.DataToDomain.Base<ApodData, T>()