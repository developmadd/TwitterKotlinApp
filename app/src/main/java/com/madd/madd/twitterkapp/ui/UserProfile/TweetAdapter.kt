package com.madd.madd.twitterkapp.ui.UserProfile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.utils.Utilities

class TweetAdapter(adapterEvents: AdapterEvents) : RecyclerView.Adapter<TweetAdapter.ViewHolder>() {


    var adapterEvents:AdapterEvents? = adapterEvents
    private var list = ArrayList<Tweet>()


    fun getList():ArrayList<Tweet>{
        return list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.section_tweet,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.imageView) lateinit var imageView:ImageView
        @BindView(R.id.textViewName) lateinit var textViewName:TextView
        @BindView(R.id.textViewDate) lateinit var textViewDate:TextView
        @BindView(R.id.textViewDescription) lateinit var textViewDescription:TextView

        init{
            ButterKnife.bind(this,itemView)
        }


        fun bind(entity:Tweet){

            Utilities.setImage(imageView,entity.user!!.photo!!)
            textViewName.text = entity.user!!.name
            textViewDescription.text = entity.description
            textViewDate.text = entity.date

            itemView.setOnClickListener {
                adapterEvents!!.onElementClick(entity)
            }


        }

    }

    interface AdapterEvents{
        fun onElementClick(entity: Tweet)
    }

}