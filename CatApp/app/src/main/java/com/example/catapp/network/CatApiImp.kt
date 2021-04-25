package com.example.catapp.network

import com.example.catapp.model.CatRequest
import javax.inject.Inject

class CatApiImp @Inject constructor(private val catApi: CatApi) {

    suspend fun getCat(limit:Int):List<CatRequest>{
        return catApi.getCat(limit = limit)
    }
}