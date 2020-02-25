package com.jaison.newsproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jaison.newsproject.LOG_TAG

import com.jaison.newsproject.R
import com.jaison.newsproject.data.ArticleItem
import com.jaison.newsproject.ui.shared.SharedViewModel
import kotlinx.android.synthetic.main.fragment_news_detail.*
import org.koin.android.ext.android.get


class MainFragment : Fragment() ,NewsRecyclerAdapter.NewsItemListener{


    private lateinit var  recyclerView:RecyclerView
    private  lateinit var navController:NavController

    //Injecting viewModel via Koin
    private val viewModel=get<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

        }
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView=view.findViewById(R.id.recyclerView)
        navController=Navigation.findNavController(
            requireActivity(),R.id.nav_host
        )
        viewModel.newsData.observe(viewLifecycleOwner, Observer {
             val adapter=NewsRecyclerAdapter(requireContext(),it,this)
             recyclerView.adapter=adapter

        })

         return view
    }

    override fun onNewsItemClick(news: ArticleItem) {
        Log.i(LOG_TAG,"Selected news ${news.title}")
        viewModel.selectedNewsItem.value=news

        //sending url via bundle in nav controller
        val bundle= bundleOf("url" to news.url)
        navController.navigate(R.id.action_nav_detail,bundle)

    }


}
