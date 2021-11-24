package com.example.spaceflightapp.data.apod.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.apod.ApodData
import com.example.spaceflightapp.data.apod.ToApodMapper

interface ApodCloudMapper : Abstract.Mapper.Data<ApodCloud, ApodData> {
    class Base(private val mapper: ToApodMapper<ApodData>) : ApodCloudMapper {
        override fun map(data: ApodCloud) = data.map(mapper)
    }
}