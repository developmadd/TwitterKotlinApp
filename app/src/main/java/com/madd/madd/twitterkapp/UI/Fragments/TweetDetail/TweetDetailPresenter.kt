package com.madd.madd.twitterkapp.UI.Fragments.TweetDetail

import com.madd.madd.twitterkapp.HTTP.Models.Tweet


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