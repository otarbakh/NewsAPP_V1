package com.example.newsapp_v1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp_v1.data.remote.models.Article
import com.example.newsapp_v1.databinding.SinglenewsitemBinding
import com.example.newsapp_v1.domain.models.ArticleDomain

class BreakingNewsAdapter :
    ListAdapter<ArticleDomain, BreakingNewsAdapter.NewsViewHolder>(
        NewsDiffCallBack()
    ) {

    private lateinit var itemClickListener: (ArticleDomain, Int) -> Unit
    private lateinit var addFavoriteListener: (ArticleDomain, Int) -> Unit

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
        private var model: ArticleDomain? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {
                tvNewsText.text = model?.title
                tvDesription.text = model?.description

                Glide.with(this.ivNewsImage)
                    .load(model?.urlToImage)
                    .into(ivNewsImage)
            }

            binding.mainLayout.setOnClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
            }

            binding.btnFavorite.setOnClickListener {
                addFavoriteListener.invoke(model!!, adapterPosition)
            }
        }
    }

    fun setOnItemClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }

    fun setOnFavoriteClickListener(clickListener: (ArticleDomain, Int) -> Unit) {
        addFavoriteListener = clickListener
    }

}

class NewsDiffCallBack :
    DiffUtil.ItemCallback<ArticleDomain>() {
    override fun areItemsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ArticleDomain,
        newItem: ArticleDomain
    ): Boolean {
        return oldItem == newItem
    }
}