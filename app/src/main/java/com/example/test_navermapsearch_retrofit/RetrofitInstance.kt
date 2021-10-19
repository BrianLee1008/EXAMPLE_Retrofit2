package com.example.test_navermapsearch_retrofit

import com.example.test_navermapsearch_retrofit.retrofit.SearchService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 싱글톤으로 구현
object RetrofitInstance {

	private val retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(MainActivity.NAVER_SEARCH_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	val searchService : SearchService by lazy {
		retrofit.create(SearchService::class.java)
	}
}