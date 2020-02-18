package com.madd.madd.twitterkapp.UI.Fragments.TweetSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.DI.App
import com.madd.madd.twitterkapp.HTTP.Models.Tweet

import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.UI.Fragments.TweetDetail.TweetDetailFragment
import com.madd.madd.twitterkapp.UI.Fragments.UserProfile.TweetAdapter
import com.madd.madd.twitterkapp.Utils.Utilities
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TweetSearchFragment : Fragment(), TweetSearchContract.View {


    @Inject
    lateinit var presenter:TweetSearchContract.Presenter

    private var adapter: TweetAdapter? = null

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.searchView) lateinit var searchView: SearchView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tweet_search, container, false)
        (activity!!.application as App).component!!.inject(this)
        ButterKnife.bind(this,v)
        loadView()

        presenter.setView(this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.requestFocus()
    }

    private fun loadView(){
        adapter = TweetAdapter(object:TweetAdapter.AdapterEvents {
            override fun onElementClick(entity: Tweet) {
                val fragment = TweetDetailFragment()
                fragment.entity = entity
                Utilities.openFragment(fragmentManager!!,fragment)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
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
        Toast.makeText(context,"Lista vacia",Toast.LENGTH_LONG).show()
    }

    override fun showInternetError() {
        Toast.makeText(context,"Sin internet",Toast.LENGTH_LONG).show()
    }

}
