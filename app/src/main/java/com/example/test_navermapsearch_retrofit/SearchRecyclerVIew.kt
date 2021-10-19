package com.example.test_navermapsearch_retrofit


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test_navermapsearch_retrofit.databinding.ItemSearchRecyclerViewBinding

class SearchRecyclerVIew : ListAdapter<SearchModel, SearchRecyclerVIew.ItemViewHolder>(differ) {

	inner class ItemViewHolder(private val binding: ItemSearchRecyclerViewBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(searchModel: SearchModel) = with(binding) {

			titleTextView.text = searchModel.title
			linkTextView.text = searchModel.link
			addressTextView.text = searchModel.address
			roadAddressTextView.text = searchModel.roadAddress
			mapxTextView.text = searchModel.mapx.toString()
			mapyTextView.text = searchModel.mapy.toString()
		}
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
		return ItemViewHolder(
			ItemSearchRecyclerViewBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false

			)
		)

	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		holder.bind(currentList[position])
	}


	companion object {
		val differ = object : DiffUtil.ItemCallback<SearchModel>() {
			override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
				return oldItem == newItem
			}

			override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
				return oldItem == newItem
			}

		}
	}
}
