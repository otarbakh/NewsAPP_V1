package com.example.newsapp_v1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp_v1.data.remote.models.Article
import com.example.newsapp_v1.databinding.SinglenewsitemBinding

class BreakingNewsAdapter :
    ListAdapter<Article, BreakingNewsAdapter.NewsViewHolder>(
        NewsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (Article, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewsViewHolder {
        val binding =
            SinglenewsitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData()
    }

    inner class NewsViewHolder(private val binding: SinglenewsitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Article? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvNewsText.text = model?.title
                tvDesription.text = model?.description

                Glide.with(this.ivNewsImage)
                    .load(model?.urlToImage)
                    .into(ivNewsImage)
            }

            binding.ivNewsImage.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (Article, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem.content == newItem.content
    }

    override fun areContentsTheSame(
        oldItem: Article,
        newItem: Article
    ): Boolean {
        return oldItem == newItem
    }
}