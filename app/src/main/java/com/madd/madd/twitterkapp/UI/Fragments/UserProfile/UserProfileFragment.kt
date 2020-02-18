package com.madd.madd.twitterkapp.UI.Fragments.UserProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.DI.App
import com.madd.madd.twitterkapp.HTTP.Models.Tweet
import com.madd.madd.twitterkapp.HTTP.Models.User

import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.UI.Fragments.TweetDetail.TweetDetailFragment
import com.madd.madd.twitterkapp.UI.Fragments.TweetRegister.TweetRegisterContract
import com.madd.madd.twitterkapp.UI.Fragments.TweetRegister.TweetRegisterFragment
import com.madd.madd.twitterkapp.UI.Fragments.TweetSearch.TweetSearchFragment
import com.madd.madd.twitterkapp.Utils.Utilities
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class UserProfileFragment : Fragment(), UserProfileContract.View {


    @Inject
    lateinit var presenter:UserProfileContract.Presenter

    private var adapter:TweetAdapter? = null

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.imageViewBack) lateinit var imageViewBack: ImageView
    @BindView(R.id.imageViewProfile) lateinit var imageViewProfile: ImageView
    @BindView(R.id.textViewName) lateinit var textViewName: TextView
    @BindView(R.id.textViewDescription) lateinit var textViewDescription: TextView
    @BindView(R.id.recyclerView) lateinit var recyclerView: RecyclerView
    @BindView(R.id.buttonAdd) lateinit var buttonAdd: ImageButton
    @BindView(R.id.buttonSearch) lateinit var buttonSearch: ImageButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_user_profile, container, false)
        (activity!!.application as App).component!!.inject(this)
        ButterKnife.bind(this,v)
        loadView()

        presenter.setView(this)
        presenter.getEntity()
        presenter.getList()
        return v
    }

    private fun loadView(){
        adapter = TweetAdapter( object : TweetAdapter.AdapterEvents {
            override fun onElementClick(entity: Tweet) {
                val fragment = TweetDetailFragment()
                fragment.entity = entity
                Utilities.openFragment(fragmentManager!!,fragment)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        buttonAdd.setOnClickListener {
            val fragment = TweetRegisterFragment()
            fragment.entityRegistered = (object:TweetRegisterContract.View.RegisterEvents{
                override fun entityRegistered(entity: Tweet) {
                    presenter.getList()
                }
            })
            Utilities.openFragment(fragmentManager!!,fragment)

        }
        buttonSearch.setOnClickListener{
            val fragment = TweetSearchFragment()
            Utilities.openFragment(fragmentManager!!,fragment)
        }
    }





    override fun showEntity(entity: User) {
        textViewName.text = entity.name
        textViewDescription.text = entity.description
        Utilities.setImage(imageViewBack,entity.backPhoto!!)
        Utilities.setImage(imageViewProfile,entity.photo!!)
    }

    override fun showList(list: List<Tweet>) {
        adapter!!.getList().addAll(list)
        adapter!!.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showEntityError() {
        Toast.makeText(context,"Error cargando usuario",Toast.LENGTH_LONG).show()
    }

    override fun showListError() {
        Toast.makeText(context,"Error cargando lista de tweets",Toast.LENGTH_LONG).show()
    }

}
