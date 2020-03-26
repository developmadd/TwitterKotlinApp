package com.madd.madd.twitterkapp.ui.TweetDetail

import com.madd.madd.twitterkapp.http.Models.Tweet

interface TweetDetailContract {

    interface View {

        fun showEntity(entity: Tweet)

        fun showLoadingProgress()
        fun hideLoadingProgress()

    }

    interface Presenter {
        fun setView(view: View)

        fun showEntity(entity:Tweet)
    }


}