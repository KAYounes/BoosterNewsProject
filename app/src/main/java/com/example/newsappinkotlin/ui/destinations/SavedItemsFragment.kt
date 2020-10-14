package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsappinkotlin.Database.DataModel
import com.example.newsappinkotlin.Database.ViewModel
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.adapter.HeadlinesRecyclerViewAdapter
import com.example.newsappinkotlin.adapter.SavedCardClickListener
import com.example.newsappinkotlin.adapter.savedAdapter
import com.example.newsappinkotlin.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_saved_items.view.*

class SavedItemsFragment : Fragment(), SavedCardClickListener {
    lateinit var recyclerViewAdapter: HeadlinesRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var newsViewModel: ViewModel
    lateinit var sharedVM: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_saved_items, container, false)

        val recyclerView = view.RecyclerView
        val adapter = savedAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        newsViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        newsViewModel.readAllData.observe(viewLifecycleOwner, Observer { news ->
            adapter.setData(news)
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*   recyclerViewAdapter = HeadlinesRecyclerViewAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        newsFeedRecyclerView.adapter = recyclerViewAdapter
        newsFeedRecyclerView.layoutManager = linearLayoutManager
    */
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getSaved().observe(viewLifecycleOwner, Observer { t ->  })
    }


    override fun onCardClick(card: DataModel, position: Int) {
        println("Headline clicked from saved")
        findNavController().navigate(R.id.action_savedItemsFragment_to_itemDetailsFragment)
        sharedVM.clickedSaved(card)
    }

}
