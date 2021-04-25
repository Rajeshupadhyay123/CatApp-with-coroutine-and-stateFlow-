package com.example.catapp.util

import com.example.catapp.model.CatRequest

sealed class APiStates{
    object Loading:APiStates()
    class Failure(val msg:Throwable):APiStates()
    class Success(val data:List<CatRequest>):APiStates()
    object Empty:APiStates()
}
