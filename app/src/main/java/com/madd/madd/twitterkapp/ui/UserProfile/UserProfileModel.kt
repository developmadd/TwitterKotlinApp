package com.madd.madd.twitterkapp.ui.UserProfile

import com.madd.madd.twitterkapp.http.API
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserProfileModel(private var api: API) : UserProfileContract.Model {


    override fun getEntity(getEntity: UserProfileContract.Model.GetEntity) {
        api.getUser().enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                if(t?.message != null) {
                    getEntity.onError(t.message!!)
                } else {
                    getEntity.onError("Unknown Error")
                }
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if(response?.body() != null) {
                    getEntity.onSuccess(response.body())
                } else {
                    getEntity.onError("Unknown Error")
                }
            }

        })
    }

    override fun getList(getList: UserProfileContract.Model.GetList) {
        api.getUserTimeLine().enqueue(object:Callback<List<Tweet>>{
            override fun onFailure(call: Call<List<Tweet>>?, t: Throwable?) {
                if(t?.message != null) {
                    getList.onError(t.message!!)
                } else {
                    getList.onError("Unknown Error")
                }
            }

            override fun onResponse(call: Call<List<Tweet>>?, response: Response<List<Tweet>>?) {
                if(response?.body() != null) {
                    getList.onSuccess(response.body())
                } else {
                    getList.onSuccess(ArrayList())
                }
            }
        })
    }
}