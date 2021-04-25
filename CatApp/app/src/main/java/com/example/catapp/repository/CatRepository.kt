package com.example.catapp.repository

import com.example.catapp.model.CatRequest
import com.example.catapp.network.CatApiImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CatRepository @Inject constructor(private val catApiImp: CatApiImp) {

    fun getCat(limit:Int):Flow<List<CatRequest>>{
        return flow {
            val response=catApiImp.getCat(limit)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}