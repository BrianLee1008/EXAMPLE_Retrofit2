package com.example.test_navermapsearch_retrofit

// 아이템 하부 리스트
data class SearchModel(
	val title : String,
	val link : String,
	val address : String,
	val roadAddress : String,
	val mapx : Int,
	val mapy : Int
) {
}