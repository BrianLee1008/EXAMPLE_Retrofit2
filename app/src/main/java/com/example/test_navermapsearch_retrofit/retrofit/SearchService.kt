package com.example.test_navermapsearch_retrofit.retrofit

import com.example.test_navermapsearch_retrofit.dto.SearchDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchService {

	// 해더, 쿼리 개발가이드 참고하면 나옴
	@GET("/v1/search/local.json")
	suspend fun search(
		@Header("X-Naver-Client-Id") clientId: String,
		@Header("X-Naver-Client-Secret") clientSecret: String,
		@Query("query") keyword : String,
		@Query("display") display : Int = 10,
		@Query("start") start : Int = 1
	) : Response<SearchDto>
	// 원래 enqueue로 비동기 처리 위해 Callback으로 반환했지만, LiveData로 할 것이니 그냥 Dto 반환
	// MainActivity에서 예외처리 위해 Response로 감싸준다.
}