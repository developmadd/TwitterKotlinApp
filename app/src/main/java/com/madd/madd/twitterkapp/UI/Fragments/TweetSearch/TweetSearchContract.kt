package com.madd.madd.twitterkapp.UI.Fragments.TweetSearch

import com.madd.madd.twitterkapp.HTTP.Models.Tweet

interface TweetSearchContract {

    interface View {

        fun showList(list: List<Tweet>)
        fun clearList()

        fun showLoadingProgress()
        fun hideLoadingProgress()
        fun showEmptyListError()
        fun showInternetError()
    }

    interface Presenter {
        fun setView(view: View)

        fun clearList()
        fun requestList(query: String)
    }

    interface Model {

        fun getList(query: String, getList: GetList)

        interface GetList {
            fun onSuccess(list: List<Tweet>)
            fun onError(error: String)
        }
    }


}