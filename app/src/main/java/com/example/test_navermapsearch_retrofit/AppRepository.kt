package com.example.test_navermapsearch_retrofit

import com.example.test_navermapsearch_retrofit.dto.SearchDto
import retrofit2.Response

class AppRepository {

	suspend fun getSearchValue(keyword: String): Response<SearchDto> {
		return RetrofitInstance.searchService.search(
			MainActivity.NAVER_SEARCH_CLIENT_ID,
			MainActivity.NAVER_SEARCH_CLIENT_KEY,
			keyword
		)
	}

}