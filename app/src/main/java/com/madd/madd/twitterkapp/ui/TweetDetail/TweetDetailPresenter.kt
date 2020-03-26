package com.madd.madd.twitterkapp.ui.TweetDetail

import com.madd.madd.twitterkapp.http.Models.Tweet


class TweetDetailPresenter : TweetDetailContract.Presenter {

    private var view: TweetDetailContract.View? = null

    override fun setView(view: TweetDetailContract.View) {
        this.view = view
    }


    override fun showEntity(entity: Tweet) {
        view!!.showLoadingProgress()
        view!!.showEntity(entity)
        view!!.hideLoadingProgress()
    }

}