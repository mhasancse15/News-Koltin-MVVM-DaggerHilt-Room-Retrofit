package com.mahmudul.newsapp.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mahmudul.newsapp.R
import com.mahmudul.newsapp.databinding.FragmentNewsDetailsBinding
import com.mahmudul.newsapp.model.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding

    // get the arguments from the Registration fragment
    private val args : NewsDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsDetailsBinding.bind(view)

        val data = requireArguments()["article"] as Article

        binding.articles = data

        Glide.with(binding.root).load(data.urlToImage).into(binding.detailsImage)


    }

}