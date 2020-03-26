package com.madd.madd.twitterkapp.ui.TweetSearch

import com.madd.madd.twitterkapp.http.Models.Tweet


class TweetSearchPresenter(private var model: TweetSearchContract.Model?) : TweetSearchContract.Presenter {


    private var view: TweetSearchContract.View? = null

    override fun setView(view: TweetSearchContract.View) {
        this.view = view
    }


    override fun clearList() {
        view!!.clearList()
    }

    override fun requestList(query: String) {
        view!!.showLoadingProgress()
        model!!.getList(query, object : TweetSearchContract.Model.GetList{

            override fun onSuccess(list: List<Tweet>) {
                if( list.isNotEmpty()) {
                    view!!.showList(list)
                } else {
                    view!!.showEmptyListError()
                }
                view!!.hideLoadingProgress()
            }

            override fun onError(error: String) {
                view!!.showInternetError()
                view!!.hideLoadingProgress()
            }

        })
    }


}