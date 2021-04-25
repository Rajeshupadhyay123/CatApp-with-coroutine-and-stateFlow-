package com.example.catapp.network

import com.example.catapp.model.CatRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {

    @GET("v1/images/search")
    suspend fun getCat(@Query("limit") limit:Int):List<CatRequest>
}