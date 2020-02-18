package com.madd.madd.twitterkapp.UI.Fragments.TweetRegister

import com.madd.madd.twitterkapp.HTTP.Models.PostTweet
import com.madd.madd.twitterkapp.HTTP.Models.Tweet
import com.madd.madd.twitterkapp.HTTP.Models.User

interface TweetRegisterContract {
    interface View {

        val text: String

        fun showLoadingProgress()
        fun hideLoadingProgress()
        fun disableForm()
        fun enableForm()

        fun showRegisterSuccess(entity: Tweet)
        fun showRegisterError()
        fun showUserError()

        fun showTextMessage(error: String, color: Int)

        fun showUser(user: User)

        interface RegisterEvents {
            fun entityRegistered(entity: Tweet)
        }
    }

    interface Presenter {
        fun setView(view: View)

        fun getUser()

        fun validateText(): Boolean
        fun register()
    }

    interface Model {

        fun registerEntity(entity: PostTweet, getEntity: GetEntity)
        fun getUser(getUser: GetUser)

        interface GetEntity {
            fun onSuccess(entity: Tweet)
            fun onError(error: String)
        }
        interface GetUser {
            fun onSuccess(entity: User)
            fun onError(error: String)
        }
    }

}