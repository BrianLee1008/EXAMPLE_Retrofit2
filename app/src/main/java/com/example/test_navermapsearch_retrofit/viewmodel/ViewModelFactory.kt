package com.example.test_navermapsearch_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.test_navermapsearch_retrofit.AppRepository


class ViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
			return MainViewModel(repository) as T
		}
		throw IllegalArgumentException("")
	}
}