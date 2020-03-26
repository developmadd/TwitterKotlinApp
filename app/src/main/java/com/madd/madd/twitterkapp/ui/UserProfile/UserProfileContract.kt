package com.madd.madd.twitterkapp.ui.UserProfile

import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User

interface UserProfileContract {
    interface View {

        fun showEntity(entity: User)
        fun showList(list: List<Tweet>)

        fun showLoadingProgress()
        fun hideLoadingProgress()
        fun showEntityError()
        fun showListError()
    }

    interface Presenter {
        fun setView(view: View)

        fun getEntity()
        fun getList()
    }

    interface Model {

        fun getEntity( getEntity: GetEntity)
        fun getList(getList: GetList)

        interface GetEntity {
            fun onSuccess(entity: User)
            fun onError(error: String)
        }

        interface GetList {
            fun onSuccess(list: List<Tweet>)
            fun onError(error: String)
        }
    }

}