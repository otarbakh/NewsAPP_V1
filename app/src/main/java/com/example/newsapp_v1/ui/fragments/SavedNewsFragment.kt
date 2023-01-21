package com.example.newsapp_v1.ui.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp_v1.databinding.FragmentSavedNewsBinding
import com.example.newsapp_v1.ui.adapters.BreakingNewsAdapter
import com.example.newsapp_v1.ui.viewmodels.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedNewsFragment :
    BaseFragment<FragmentSavedNewsBinding>(FragmentSavedNewsBinding::inflate) {

    private val breakingNewsAdapter: BreakingNewsAdapter by lazy { BreakingNewsAdapter() }

    private val vm: NewsViewModel by viewModels()

    override fun viewCreated() {
        getSavedArticles()

    }

    override fun listeners() {
        swipe()
    }

    private fun getSavedArticles() {
        setupRecycler()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.getSavedArticle().collectLatest {
                    breakingNewsAdapter.submitList(it)
                }
            }
        }

    }

    private fun swipe(){
        val itemTouchHelper = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = breakingNewsAdapter.currentList[position]
                vm.deleteArticle(article)

                Snackbar.make(view!!,"Successfully deleted article",Snackbar.LENGTH_LONG).apply {
                    setAction("Undo"){
                        vm.upsertArticle(article)
                    }
                    show()
                }
            }
        }
        ItemTouchHelper(itemTouchHelper).apply {
            attachToRecyclerView(binding.rvSavedItems)
        }
    }


    private fun setupRecycler() {
        binding.rvSavedItems.apply {
            adapter = breakingNewsAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
    }



}