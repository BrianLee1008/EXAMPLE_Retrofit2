package com.example.test_navermapsearch_retrofit

// 대괄호 안 아이템
data class SearchDto(
	var lastBuildDate: String,
	val total : Int,
	val start : Int,
	val display : Int,
	val items : List<SearchModel>
) {
}
