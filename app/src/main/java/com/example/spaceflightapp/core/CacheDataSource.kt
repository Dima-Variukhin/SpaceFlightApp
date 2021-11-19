package com.example.spaceflightapp.core

interface CacheDataSource<D : Abstract.DataObject> : Save<List<D>>, Delete