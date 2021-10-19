package com.example.test_navermapsearch_retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

	// 해더, 쿼리 개발가이드 참고하면 나옴
	@GET("/v1/search/local.json")
	fun search(
		@Header("X-Naver-Client-Id") clientId: String,
		@Header("X-Naver-Client-Secret") clientSecret: String,
		@Query("query") keyword : String,
		@Query("display") display : Int = 10,
		@Query("start") start : Int = 1
	) : Call<SearchDto>
}