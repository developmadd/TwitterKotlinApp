package com.madd.madd.twitterkapp.UI.Fragments.TweetRegister


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.DI.App
import com.madd.madd.twitterkapp.HTTP.Models.Tweet
import com.madd.madd.twitterkapp.HTTP.Models.User

import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.Utils.Utilities
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TweetRegisterFragment : Fragment(), TweetRegisterContract.View {



    override val text: String
        get() {
            return editText.text.toString()
        }



    @Inject
    lateinit var presenter:TweetRegisterContract.Presenter

    @BindView(R.id.progressBar) lateinit var progressBar: ProgressBar
    @BindView(R.id.imageView) lateinit var imageView: ImageView
    @BindView(R.id.textViewName) lateinit var textViewName: TextView
    @BindView(R.id.textViewCounter) lateinit var textViewCounter: TextView
    @BindView(R.id.editText) lateinit var editText: EditText
    @BindView(R.id.button) lateinit var button: Button

    var user: User? = null
    var entityRegistered:TweetRegisterContract.View.RegisterEvents? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tweet_register, container, false)
        (activity!!.application as App).component!!.inject(this)
        ButterKnife.bind(this,v)
        loadView()

        presenter.setView(this)
        presenter.getUser()

        return v
    }

    private fun loadView(){

        button.setOnClickListener {
            presenter.register()
        }

        editText.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.validateText()
            }
        })

    }





    override fun showUser(user: User) {
        Utilities.setImage(imageView,user.photo!!)
        textViewName.text = user.name
    }

    override fun showLoadingProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar.visibility = View.GONE
    }

    override fun disableForm() {
        button.isEnabled = false
    }

    override fun enableForm() {
        button.isEnabled = true
    }

    override fun showRegisterSuccess(entity: Tweet) {
        Toast.makeText(context,"Registro exitoso",Toast.LENGTH_LONG).show()
        entityRegistered!!.entityRegistered(entity)
        fragmentManager!!.popBackStack()
    }

    override fun showRegisterError() {
        Toast.makeText(context,"Error durante registro",Toast.LENGTH_LONG).show()
    }

    override fun showUserError() {
        Toast.makeText(context,"Error obteniendo usuario",Toast.LENGTH_LONG).show()
        fragmentManager!!.popBackStack()
    }

    override fun showTextMessage(error: String, color: Int) {
        textViewCounter.text = error
        textViewCounter.setTextColor(color)
    }

}
