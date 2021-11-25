package com.myapp.spaceflightapp.data.apod.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.apod.ApodData
import com.myapp.spaceflightapp.data.apod.ToApodMapper

interface ApodCloudMapper : Abstract.Mapper.Data<ApodCloud, ApodData> {
    class Base(private val mapper: ToApodMapper<ApodData>) : ApodCloudMapper {
        override fun map(data: ApodCloud) = data.map(mapper)
    }
}