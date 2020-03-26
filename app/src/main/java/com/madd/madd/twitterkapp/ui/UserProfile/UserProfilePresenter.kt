package com.madd.madd.twitterkapp.ui.UserProfile

import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User


class UserProfilePresenter(private var model: UserProfileContract.Model?) : UserProfileContract.Presenter {


    private var view: UserProfileContract.View? = null

    override fun setView(view: UserProfileContract.View) {
        this.view = view
    }

    override fun getEntity() {
        view!!.showLoadingProgress()
        model!!.getEntity(object:UserProfileContract.Model.GetEntity{
            override fun onSuccess(entity: User) {
                view!!.showEntity(entity)
                view!!.hideLoadingProgress()
            }

            override fun onError(error: String) {
                view!!.showEntityError()
                view!!.hideLoadingProgress()
            }
        })
    }

    override fun getList() {
        view!!.showLoadingProgress()
        model!!.getList(object : UserProfileContract.Model.GetList{
            override fun onSuccess(list: List<Tweet>) {
                if(list.isNotEmpty()) {
                    view!!.showList(list)
                } else {
                    view!!.showListError()
                }
                view!!.hideLoadingProgress()
            }

            override fun onError(error: String) {
                view!!.showListError()
                view!!.hideLoadingProgress()
            }
        })
    }

}