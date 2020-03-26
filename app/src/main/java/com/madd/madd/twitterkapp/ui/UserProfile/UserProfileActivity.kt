package com.madd.madd.twitterkapp.ui.UserProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.di.App
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User
import com.madd.madd.twitterkapp.ui.TweetDetail.TweetDetailActivity
import com.madd.madd.twitterkapp.ui.TweetRegister.TweetRegisterActivity
import com.madd.madd.twitterkapp.ui.TweetSearch.TweetSearchActivity
import com.madd.madd.twitterkapp.utils.Utilities
import javax.inject.Inject

class UserProfileActivity : AppCompatActivity(), UserProfileContract.View {


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).component!!.inject(this)
        ButterKnife.bind(this)
        loadView()

        presenter.setView(this)
        presenter.getEntity()
        presenter.getList()

    }



    private fun loadView(){
        adapter = TweetAdapter( object : TweetAdapter.AdapterEvents {
            override fun onElementClick(entity: Tweet) {
                val intent = Intent()
                intent.setClass(applicationContext, TweetDetailActivity::class.java)
                intent.putExtra("tweet", entity)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        buttonAdd.setOnClickListener {
            val intent = Intent()
            intent.setClass(applicationContext, TweetRegisterActivity::class.java)
            startActivityForResult(intent,TweetRegisterActivity.REGISTER_TWEET)
        }
        buttonSearch.setOnClickListener{
            val intent = Intent()
            intent.setClass(applicationContext, TweetSearchActivity::class.java)
            startActivity(intent)
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
        Toast.makeText(this,"Error cargando usuario", Toast.LENGTH_LONG).show()
    }

    override fun showListError() {
        Toast.makeText(this,"Error cargando lista de tweets", Toast.LENGTH_LONG).show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TweetRegisterActivity.REGISTER_TWEET &&
             resultCode == TweetRegisterActivity.TWEET_REGISTERED) {
            adapter!!.getList().clear()
            presenter.getList()
        }

    }

}
