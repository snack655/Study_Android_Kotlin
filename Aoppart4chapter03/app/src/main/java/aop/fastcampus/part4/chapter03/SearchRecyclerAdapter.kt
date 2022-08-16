package aop.fastcampus.part4.chapter03

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aop.fastcampus.part4.chapter03.databinding.ViewholderSearchResultItemBinding

class SearchRecyclerAdapter : RecyclerView.Adapter<SearchRecyclerAdapter.SearchResultItemViewHolder>() {

    private var searchResultList: List<Any> = listOf()
    lateinit var searchResultClickListener: (Any) -> Unit

    class SearchResultItemViewHolder(val binding: ViewholderSearchResultItemBinding, val searchResultClickListener: (Any) -> Unit): RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Any) = with(binding) {
            textTextView.text = "제목"
            subtextTextView.text = "부제목"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultItemViewHolder {
        val view = ViewholderSearchResultItemBinding.bind(parent)
        return SearchResultItemViewHolder(view, searchResultClickListener)
    }

    override fun onBindViewHolder(holder: SearchResultItemViewHolder, position: Int) {
        holder.bindData(searchResultList[position])
    }

    override fun getItemCount(): Int = 10
}