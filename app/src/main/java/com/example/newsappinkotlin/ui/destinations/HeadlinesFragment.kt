package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HeaderViewListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.adapter.CardClickListener
import com.example.newsappinkotlin.adapter.HeadlinesRecyclerViewAdapter
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment(), CardClickListener {
    lateinit var sharedVM: SharedViewModel
    lateinit var recyclerViewAdapter: HeadlinesRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewAdapter = HeadlinesRecyclerViewAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        newsFeedRecyclerView.adapter = recyclerViewAdapter
        newsFeedRecyclerView.layoutManager = linearLayoutManager

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("required activity is ${requireActivity()}")
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getHeadlines().observe(viewLifecycleOwner, Observer { t -> printTitles(t) })
    }


    override fun onCardClick(card: FullNewsModel, position: Int) {
        findNavController().navigate(R.id.action_headlinesFragment_to_itemDetailsFragment)
        sharedVM.clickedHeadline(card)
    }


    private fun printTitles(list: ArrayList<FullNewsModel>){
        for (i in 0..list.size){
            println(">$i = ${list[i].headLineTitle}")
        }
    }
}





