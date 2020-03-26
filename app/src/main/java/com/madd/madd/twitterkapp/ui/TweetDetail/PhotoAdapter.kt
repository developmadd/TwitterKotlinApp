package com.madd.madd.twitterkapp.ui.TweetDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.utils.Utilities

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {



    private var list = ArrayList<String>()


    fun getList():ArrayList<String>{
        return list
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.section_e,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.imageView)   lateinit var imageView:ImageView

        init {
            ButterKnife.bind(this,itemView)
        }

        fun bind(entity:String){

            Utilities.setImage(imageView,entity)

        }

    }



}