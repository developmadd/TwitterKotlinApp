package com.madd.madd.twitterkapp.ui.TweetRegister

import android.graphics.Color
import com.madd.madd.twitterkapp.http.Models.PostTweet
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User


class TweetRegisterPresenter(private var model: TweetRegisterContract.Model?) : TweetRegisterContract.Presenter {


    private var view: TweetRegisterContract.View? = null

    override fun setView(view: TweetRegisterContract.View) {
        this.view = view
    }

    override fun getUser() {

        view!!.showLoadingProgress()
        model!!.getUser(object:TweetRegisterContract.Model.GetUser{
            override fun onSuccess(entity: User) {
                view!!.showUser(entity)
                view!!.hideLoadingProgress()
            }

            override fun onError(error: String) {
                view!!.showUserError()
                view!!.hideLoadingProgress()
            }

        })
    }


    override fun validateText(): Boolean {
        if( view!!.text.isEmpty() ){
            view!!.showTextMessage("0/140 caracteres",Color.RED)
            return false
        } else if( view!!.text.length > 140 ){
            view!!.showTextMessage("0/" + view!!.text.length + " caracteres",Color.RED)
            return false
        } else {
            view!!.showTextMessage("0/" + view!!.text.length + " caracteres",Color.BLACK)
            return true
        }
    }

    override fun register() {

        if( formValidation() ) {
            view!!.showLoadingProgress()
            val postTweet = PostTweet()
            postTweet.status = view!!.text
            model!!.registerEntity(postTweet, object : TweetRegisterContract.Model.GetEntity{
                override fun onSuccess(entity: Tweet) {
                    view!!.showRegisterSuccess(entity)
                    view!!.hideLoadingProgress()
                }

                override fun onError(error: String) {
                    view!!.showRegisterError()
                    view!!.hideLoadingProgress()
                }

            })
        }
    }


    private fun formValidation():Boolean{
        var validForm = true
        if (!validateText()) {
            validForm = false
        }
        return validForm
    }

}