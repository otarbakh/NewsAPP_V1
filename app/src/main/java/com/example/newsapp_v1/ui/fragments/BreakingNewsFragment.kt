package com.example.newsapp_v1.ui.fragments


import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp_v1.databinding.FragmentBreakingNewsBinding
import com.example.newsapp_v1.ui.adapters.BreakingNewsAdapter

import com.example.newsapp_v1.ui.viewmodels.NewsViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BreakingNewsFragment :
    BaseFragment<FragmentBreakingNewsBinding>(FragmentBreakingNewsBinding::inflate) {

    private val breakingNewsAdapter: BreakingNewsAdapter by lazy { BreakingNewsAdapter() }

    private val vm: NewsViewModel by viewModels()


    override fun viewCreated() {
        setupRecycler()
        observe()
    }

    override fun listeners() {
        gotoLink()
        addToFavorite()
        search()
    }

    private fun setupRecycler() {
        binding.rvBreakingNews.apply {
            adapter = breakingNewsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }



    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.collectedSearchQuerry.collectLatest {
                    breakingNewsAdapter.submitData(it)

                }

            }
        }
    }



    private fun gotoLink() {
        breakingNewsAdapter.apply {
            setOnItemClickListener { article, i ->
                val uri: Uri = Uri.parse(article.url) // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }
    }

    private fun search() {
        binding.etSearchImpl.addTextChangedListener { editable ->
            if (editable!!.toString().isNotEmpty()) {
                vm.search(editable.toString())
            }
        }
    }

    private fun addToFavorite() {
        breakingNewsAdapter.apply {
            setOnFavoriteClickListener { article, i ->
                vm.upsertArticle(article)
            }
        }
    }

}