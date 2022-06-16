package fastcampus.aop.part3.chapter04.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fastcampus.aop.part3.chapter04.databinding.ItemBookBinding
import fastcampus.aop.part3.chapter04.databinding.ItemHistoryBinding
import fastcampus.aop.part3.chapter04.model.Book
import fastcampus.aop.part3.chapter04.model.History

class HistoryAdapter(val historyDeleteClickedListener: (String) -> Unit)  : ListAdapter<History, HistoryAdapter.HistoryItemViewHolder>(diffUtil) {

    inner class HistoryItemViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(historyModel: History) {
            binding.historyKeywordTextView.text = historyModel.keyword

            binding.historyKeywordDeleteButton.setOnClickListener {
                historyDeleteClickedListener(historyModel.keyword.orEmpty())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        return HistoryItemViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.keyword == newItem.keyword
            }
        }
    }
}