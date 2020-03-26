package com.madd.madd.twitterkapp.ui.TweetRegister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.twitterkapp.R
import com.madd.madd.twitterkapp.di.App
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User
import com.madd.madd.twitterkapp.utils.Utilities
import javax.inject.Inject

class TweetRegisterActivity : AppCompatActivity(), TweetRegisterContract.View {

    companion object{
        val REGISTER_TWEET = 0
        val TWEET_REGISTERED = 1
    }


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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_register)

        (application as App).component!!.inject(this)
        ButterKnife.bind(this)
        loadView()

        presenter.setView(this)
        presenter.getUser()
    }



    private fun loadView(){

        button.setOnClickListener {
            presenter.register()
        }

        editText.addTextChangedListener(object : TextWatcher {
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
        Toast.makeText(this,"Registro exitoso",Toast.LENGTH_LONG).show()
        setResult(TWEET_REGISTERED)
        finish()
    }

    override fun showRegisterError() {
        Toast.makeText(this,"Error durante registro",Toast.LENGTH_LONG).show()
    }

    override fun showUserError() {
        Toast.makeText(this,"Error obteniendo usuario",Toast.LENGTH_LONG).show()
        finish()
    }

    override fun showTextMessage(error: String, color: Int) {
        textViewCounter.text = error
        textViewCounter.setTextColor(color)
    }
}
