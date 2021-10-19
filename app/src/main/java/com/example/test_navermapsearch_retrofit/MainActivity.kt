package com.example.test_navermapsearch_retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_navermapsearch_retrofit.adapter.SearchRecyclerVIew
import com.example.test_navermapsearch_retrofit.databinding.ActivityMainBinding
import com.example.test_navermapsearch_retrofit.viewmodel.MainViewModel
import com.example.test_navermapsearch_retrofit.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

	private val searchAdapter = SearchRecyclerVIew()

	private lateinit var viewModel: MainViewModel
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val repository = AppRepository()
		viewModel = ViewModelProvider(this, ViewModelFactory(repository))[MainViewModel::class.java]
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)



		initRecyclerView()
		setButtonListener()

	}

	private fun initRecyclerView() = with(binding) {
		recyclerView.adapter = searchAdapter
		recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

	}

	private fun setButtonListener() = with(binding) {
		button.setOnClickListener {
			viewModel.getSearchValue(searchEditText.text.toString())

			retrofitTest()
		}

	}

	// enqueue로 레트로핏 이벤트 콜백 가져오는 게 아니라 LiveData로 지속적 관찰 형식.
	// Service Interface에선 Callback 형식으로 반환해야할게 사라지고 그냥 Dto로 반환해 Repo에서  CRUD로 반환
	// Callback으로 반환했던 이유는 enqueue로 통신완료 후 콜백으로 비동기 실행을 해야하고 
	// 별도의 트랙잭션 이후에 콜백 함수 호출해야했기 때문에 LiveData로 할 시 코루틴으로 작업 스케쥴링 해야함. 안그러면 Dto 호출 안됨.
	// https://jaejong.tistory.com/33
	private fun retrofitTest() {
		viewModel.searchResponse
			.observe(this, Observer {
				// 예외처리
				if (it.isSuccessful.not()) {
					return@Observer
				}
				it.body()?.let { dto ->
					searchAdapter.submitList(dto.items)
				}

			})
	}

	companion object {
		const val NAVER_SEARCH_CLIENT_ID = "tcW_9JHVeJLBE_wmR8S0"
		const val NAVER_SEARCH_CLIENT_KEY = "tedcXYPP2X"
		const val NAVER_SEARCH_BASE_URL = "https://openapi.naver.com"
	}











	// 기존방법
//	private fun getSearchFromRetrofit(keyword: String) {
//		retrofit.create(SearchService::class.java).also { it ->
//			it.search(
//				clientId = NAVER_SEARCH_CLIENT_ID,
//				clientSecret = NAVER_SEARCH_CLIENT_KEY,
//				keyword = keyword
//			).enqueue(object : Callback<SearchDto> {
//				override fun onResponse(call: Call<SearchDto>, response: Response<SearchDto>) {
//
//					if (response.isSuccessful.not()) {
//						Log.d("retrofit", "실패 : $response")
//						return
//					}
//
//					response.body()?.let { dto ->
//						Log.d("retrofit", "성공 : ${response.body()}")
//						searchAdapter.submitList(dto.items)
//					}
//				}
//
//				override fun onFailure(call: Call<SearchDto>, t: Throwable) {
//					Log.d("retrofit", "실패 : $t")
//				}
//
//			})
//		}
//	}
}