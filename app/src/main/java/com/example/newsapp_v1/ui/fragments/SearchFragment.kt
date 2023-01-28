//package com.example.newsapp_v1.ui.fragments
//
//
//import androidx.core.widget.addTextChangedListener
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.lifecycleScope
//import androidx.lifecycle.repeatOnLifecycle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.newsapp_v1.databinding.FragmentSearchBinding
//import com.example.newsapp_v1.ui.adapters.BreakingNewsAdapter
//import com.example.newsapp_v1.ui.viewmodels.NewsViewModel
//import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.launch
//
//
//@AndroidEntryPoint
//class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
//
//    private val breakingNewsAdapter: BreakingNewsAdapter by lazy { BreakingNewsAdapter() }
//
//    private val vm: NewsViewModel by viewModels()
//    override fun listeners() {
////                search()
//
//    }
//
//
//    override fun viewCreated() {
//        setupRecycler()
//        observe()
//    }
//
//    private fun observe() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                vm.searchState.collect() {
//                    breakingNewsAdapter.submitData(it)
//                }
//            }
//        }
//    }
//
//    private suspend fun search() {
//        binding.etSearch.addTextChangedListener { editable ->
//            if (editable!!.toString().isNotEmpty()) {
//                viewLifecycleOwner.lifecycleScope.launch {
//                    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                        vm.getSearchedNews(editable.toString())
//                    }
//                }
//            }
//        }
//    }
//
//    private fun setupRecycler() {
//        binding.rvSearchNews.apply {
//            adapter = breakingNewsAdapter
//            layoutManager =
//                LinearLayoutManager(
//                    requireContext(),
//                    LinearLayoutManager.VERTICAL,
//                    false
//                )
//        }
//    }
//
//
//}