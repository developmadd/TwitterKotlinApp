package com.madd.madd.twitterkapp.ui.TweetSearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.di.App
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.ui.TweetDetail.TweetDetailActivity
import com.madd.madd.twitterkapp.ui.UserProfile.TweetAdapter
import javax.inject.Inject

class TweetSearchActivity : AppCompatActivity(), TweetSearchContract.View {

    @Inject
    lateinit var presenter:TweetSearchContract.Presenter

    private var adapter: TweetAdapter? = null

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.searchView) lateinit var searchView: SearchView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_search)

        (application as App).component!!.inject(this)
        ButterKnife.bind(this)
        loadView()

        presenter.setView(this)

    }



    private fun loadView(){
        adapter = TweetAdapter(object:TweetAdapter.AdapterEvents {
            override fun onElementClick(entity: Tweet) {
                val intent = Intent()
                intent.setClass(applicationContext, TweetDetailActivity::class.java)
                intent.putExtra("tweet", entity)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.clearList()
                presenter.requestList(query!!)
                return false
            }
        })

    }







    override fun showList(list: List<Tweet>) {
        adapter!!.getList().addAll(list)
        adapter!!.notifyDataSetChanged()
    }

    override fun clearList() {
        adapter!!.getList().clear()
        adapter!!.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showEmptyListError() {
        Toast.makeText(this,"Lista vacia", Toast.LENGTH_LONG).show()
    }

    override fun showInternetError() {
        Toast.makeText(this,"Sin internet", Toast.LENGTH_LONG).show()
    }


}
