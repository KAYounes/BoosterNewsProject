package com.example.newsappinkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappinkotlin.Database.DataModel
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import kotlinx.android.synthetic.main.fragment_headline_card.view.*

class savedAdapter(private var clickListener: SavedCardClickListener) : RecyclerView.Adapter<savedAdapter.MyViewHolder>(){

    private var newsList = emptyList<DataModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun onBind(headline: DataModel, action: SavedCardClickListener){
            itemView.setOnClickListener{
                action.onCardClick(headline, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.savedcard,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = newsList[position]
        holder.itemView.headlineTitle.text = currentItem.title
        holder.itemView.headlineSource.text = currentItem.source
        holder.itemView.headlineThumbnail.setImageResource(R.drawable.yellow_globe)
        holder.onBind(currentItem, clickListener)


    }

    override fun getItemCount(): Int {
       return newsList.size
    }

    fun setData(news: List<DataModel>){

        this.newsList =  news
        notifyDataSetChanged()
    }

}

interface SavedCardClickListener{
    fun onCardClick(card: DataModel, position: Int)
}