package com.mahmudul.newsapp.view.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.google.android.material.snackbar.Snackbar
import com.mahmudul.newsapp.R
import com.mahmudul.newsapp.adapter.AdapterClicklListioners
import com.mahmudul.newsapp.adapter.NewsPagingAdapter
import com.mahmudul.newsapp.databinding.FragmentNewsBinding
import com.mahmudul.newsapp.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() , AdapterClicklListioners {

    private lateinit var binding: FragmentNewsBinding


    val viewModel: NewsViewModel by viewModels()

    private val newsPagingAdapter = NewsPagingAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsBinding.bind(view)


        viewModel.list.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(lifecycle, it)
        }

        newsPagingAdapter.addLoadStateListener { state ->

            when (state.refresh) {
                is LoadState.Loading -> {
                    binding.newsProgress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.newsProgress.visibility = View.GONE
                }
                is LoadState.Error -> {
                    binding.newsProgress.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()
                }

            }

        }
        binding.newsRecycler.adapter = newsPagingAdapter

    }

    override fun clickListioners(article: Article) {
        findNavController().navigate(
            R.id.action_newsFragment_to_newsDetailsFragment,
            bundleOf("article" to article)
        )
    }

}