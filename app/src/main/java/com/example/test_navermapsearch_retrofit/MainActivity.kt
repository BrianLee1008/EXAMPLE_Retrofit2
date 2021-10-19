package com.example.test_navermapsearch_retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_navermapsearch_retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

	private lateinit var retrofit: Retrofit
	private val searchAdapter = SearchRecyclerVIew()

	private lateinit var binding : ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		retrofit = Retrofit.Builder()
			.baseUrl(NAVER_SEARCH_BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()

		initRecyclerView()
		setButtonListener()

	}

	private fun initRecyclerView() = with(binding){
		recyclerView.adapter = searchAdapter
		recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

	}

	private fun setButtonListener()=with(binding){
		button.setOnClickListener {
			getSearchFromRetrofit(searchEditText.text.toString())
		}

	}


	private fun getSearchFromRetrofit(keyword : String){
		retrofit.create(SearchService::class.java).also { it ->
			it.search(
				clientId = NAVER_SEARCH_CLIENT_ID,
				clientSecret = NAVER_SEARCH_CLIENT_KEY,
				keyword = keyword
			).enqueue(object : Callback<SearchDto>{
				override fun onResponse(call: Call<SearchDto>, response: Response<SearchDto>) {

					if(response.isSuccessful.not()){
						Log.d("retrofit","실패 : $response")
						return
					}

					response.body()?.let { dto ->
						Log.d("retrofit","성공 : ${response.body()}")
						searchAdapter.submitList(dto.items)
					}
				}

				override fun onFailure(call: Call<SearchDto>, t: Throwable) {
					Log.d("retrofit","실패 : $t")
				}

			})
		}
	}

	companion object{
		const val NAVER_SEARCH_CLIENT_ID = "tcW_9JHVeJLBE_wmR8S0"
		const val NAVER_SEARCH_CLIENT_KEY = "tedcXYPP2X"
		const val NAVER_SEARCH_BASE_URL = "https://openapi.naver.com"
	}
}