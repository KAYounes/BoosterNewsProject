package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsappinkotlin.Database.DataModel
import com.example.newsappinkotlin.Database.ViewModel
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {
    lateinit var sharedVM: SharedViewModel
    private lateinit var newsViewModel: ViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{ val view = inflater.inflate(R.layout.fragment_item_details, container, false)


    newsViewModel = ViewModelProvider(this).get(ViewModel::class.java)

    view.saveNews.setOnClickListener{
        insertDatatoDatabase()
    }

    return view
}
private fun insertDatatoDatabase(){

    val title = newsTitle.text.toString()
    val source = newsSource.text.toString()
    val publish = newslPublishTime.text.toString()
    val description = newsDescription.text.toString()
    val content = newsContent.text.toString()


    val news = DataModel(0,title, description,publish,content,source)

    newsViewModel.saveNews(news)

    Toast.makeText(requireContext(),"sucessfully saved!", Toast.LENGTH_LONG).show()


}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedVM = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        sharedVM.getHeadline().observe(viewLifecycleOwner, Observer { t -> bind(t)  })
        sharedVM.getSaved().observe(viewLifecycleOwner, Observer { t -> bind(t) })

        }


    fun bind(card: FullNewsModel){
        Glide.with(this).load(card.headLineThumbNail).error(R.drawable.yellow_globe).into(newsThumbnail)
        newsTitle.text = card.headLineTitle
        newslPublishTime.text = card.headLinePublish
        newsSource.text = card.headLineSource.name
        if(card.headLineThumbNail == null){
            newsDescription.text = "Visit video @ ${card.visit}"
        }else{
        newsDescription.text = card.newsDescription
        }
        newsContent.text = card.newsContent
    }

    fun bind(card: DataModel){
//        Glide.with(this).load(newsThumbnail).error(R.drawable.yellow_globe).into(newsThumbnail)
        newsTitle.text = card.title
        newslPublishTime.text = card.publish
        newsSource.text = card.source
        newsDescription.text = card.description
        newsContent.text = card.content
//        newsThumbnail.setImageResource(R.drawable.yellow_globe)
        }

}
