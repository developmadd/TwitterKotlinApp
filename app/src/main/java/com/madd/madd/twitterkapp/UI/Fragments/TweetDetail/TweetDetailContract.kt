package com.madd.madd.twitterkapp.UI.Fragments.TweetDetail

import com.madd.madd.twitterkapp.HTTP.Models.Tweet

interface TweetDetailContract {

    interface View {

        val entity: Tweet

        fun showEntity(entity: Tweet)

        fun showLoadingProgress()
        fun hideLoadingProgress()

    }

    interface Presenter {
        fun setView(view: View)

        fun showEntity(entity:Tweet)
    }


}