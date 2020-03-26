package com.madd.madd.twitterkapp.ui.TweetDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.di.App
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.utils.Utilities
import javax.inject.Inject

class TweetDetailActivity : AppCompatActivity(), TweetDetailContract.View {



    lateinit var adapter:PhotoAdapter

    @Inject
    lateinit var presenter:TweetDetailContract.Presenter

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.imageViewProfile) lateinit var imageView: ImageView
    @BindView(R.id.textViewName) lateinit var textViewName: TextView
    @BindView(R.id.textViewDate) lateinit var textViewDate: TextView
    @BindView(R.id.textViewTweet) lateinit var textViewTweet: TextView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_detail)

        (application as App).component!!.inject(this)
        ButterKnife.bind(this)
        loadView()

        val tweet = intent.getSerializableExtra("tweet") as Tweet

        presenter.setView(this)
        presenter.showEntity(tweet)
    }


    private fun loadView(){

        adapter = PhotoAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

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
