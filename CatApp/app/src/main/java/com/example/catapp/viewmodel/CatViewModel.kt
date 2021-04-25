package com.example.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.repository.CatRepository
import com.example.catapp.util.APiStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(private val catRepository: CatRepository):ViewModel() {

    private var _mutableCatStateFlow=MutableStateFlow<APiStates>(APiStates.Empty)

    val catStateFlow:StateFlow<APiStates>
    get() = _mutableCatStateFlow

    init {
        getCat()
    }

    fun getCat(){
        viewModelScope.launch {
            _mutableCatStateFlow.value=APiStates.Loading
            catRepository.getCat(20)
                .catch {
                    _mutableCatStateFlow.value=APiStates.Failure(it)
                }
                .collect {data->
                    _mutableCatStateFlow.value=APiStates.Success(data)
                }
        }
    }
}