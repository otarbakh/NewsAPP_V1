package com.example.newsapp_v1.ui.fragments


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsapp_v1.databinding.FragmentSavedNewsBinding
import com.example.newsapp_v1.ui.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SavedNewsFragment :
    BaseFragment<FragmentSavedNewsBinding>(FragmentSavedNewsBinding::inflate) {
    private val vm: NewsViewModel by viewModels()

    override fun viewCreated() {
//        vm.gettest()
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//                vm.test.collect(){
//                    binding.test.text = it
//                }
//            }
//        }

    }

    override fun listeners() {

    }

}