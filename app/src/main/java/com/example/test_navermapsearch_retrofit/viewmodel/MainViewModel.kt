package com.example.test_navermapsearch_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_navermapsearch_retrofit.AppRepository
import com.example.test_navermapsearch_retrofit.dto.SearchDto
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: AppRepository) : ViewModel() {

	private var _searchResponse = MutableLiveData<Response<SearchDto>>()
	val searchResponse : LiveData<Response<SearchDto>>
		get() = _searchResponse

	fun getSearchValue(keyword : String){
		viewModelScope.launch {
			val response = repository.getSearchValue(keyword)
			_searchResponse.value = response
		}

	}


}