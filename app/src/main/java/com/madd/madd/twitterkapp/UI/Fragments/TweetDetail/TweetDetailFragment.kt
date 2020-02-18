package com.madd.madd.twitterkapp.UI.Fragments.TweetDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.DI.App
import com.madd.madd.twitterkapp.HTTP.Models.Tweet

import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.Utils.Utilities
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TweetDetailFragment : Fragment(), TweetDetailContract.View {



    override lateinit var entity: Tweet

    lateinit var adapter:PhotoAdapter

    @Inject
    lateinit var presenter:TweetDetailContract.Presenter

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.imageViewProfile) lateinit var imageView: ImageView
    @BindView(R.id.textViewName) lateinit var textViewName: TextView
    @BindView(R.id.textViewDate) lateinit var textViewDate: TextView
    @BindView(R.id.textViewTweet) lateinit var textViewTweet: TextView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tweet_detail, container, false)
        (activity!!.application as App).component!!.inject(this)
        ButterKnife.bind(this,v)
        loadView()

        presenter.setView(this)
        presenter.showEntity(entity)
        return v
    }

    private fun loadView(){

        adapter = PhotoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

    }



    override fun showEntity(entity: Tweet) {
        Utilities.setImage(imageView,entity.user!!.photo!!)
        textViewName.text = entity.user!!.name
        textViewDate.text = entity.date
        textViewTweet.text = entity.description
        if (entity.entities!!.media != null) {
            for (media in entity.entities!!.media!!) {
                adapter.getList().add(media.photo!!)
            }
            adapter.notifyDataSetChanged()
        }

    }

    override fun showLoadingProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar.visibility = View.GONE
    }




}
