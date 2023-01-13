package com.example.newsapp_v1.ui.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsapp_v1.databinding.FragmentBreakingNewsBinding
import com.example.newsapp_v1.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreakingNewsFragment :
    BaseFragment<FragmentBreakingNewsBinding>(FragmentBreakingNewsBinding::inflate) {
    private val vm: NewsViewModel by viewModels()

    override fun viewCreated() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.getBreakingNewsFromViewModel()
            }
        }
    }

    override fun listeners() {

    }


}