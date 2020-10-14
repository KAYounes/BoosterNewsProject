package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : Fragment() {
    lateinit var sharedVM: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_item_details, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getHeadline().observe(viewLifecycleOwner, Observer { t -> bind(t)  })
    }


    fun bind(card: FullNewsModel){
        Glide.with(this).load(card.headLineThumbNail).error(R.drawable.yellow_globe).into(newsThumbnail)
        newsTitle.text = card.headLineTitle
        newslPublishTime.text = card.headLinePublish
        newsSource.text = card.headLineSource.name
        newsDescription.text = card.newsDescription
        newsContent.text = card.newsContent
    }

}