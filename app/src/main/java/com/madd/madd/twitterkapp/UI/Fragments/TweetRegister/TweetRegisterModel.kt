package com.madd.madd.twitterkapp.UI.Fragments.TweetRegister

import com.madd.madd.twitterkapp.HTTP.API
import com.madd.madd.twitterkapp.HTTP.Models.PostTweet
import com.madd.madd.twitterkapp.HTTP.Models.Tweet
import com.madd.madd.twitterkapp.HTTP.Models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetRegisterModel(private var api: API) : TweetRegisterContract.Model {


    override fun getUser(getUser: TweetRegisterContract.Model.GetUser) {
        api.getUser().enqueue(object:Callback<User>{
            override fun onFailure(call: Call<User>?, t: Throwable?) {
                if( t != null ){
                    getUser.onError(t.message!!)
                } else {
                    getUser.onError("Unkown Error")
                }
            }

            override fun onResponse(call: Call<User>?, response: Response<User>?) {
                if( response?.body() != null ){
                    getUser.onSuccess(response.body())
                } else {
                    getUser.onError("Unknown Error")
                }
            }
        })
    }

    override fun registerEntity(entity: PostTweet,
        getEntity: TweetRegisterContract.Model.GetEntity
    ) {
        api.registerTweet(entity).enqueue(object: Callback<Tweet>{
            override fun onFailure(call: Call<Tweet>?, t: Throwable?) {
                if( t?.message != null) {
                    getEntity.onError(t.message!!)
                } else {
                    getEntity.onError("Unknown Error")
                }

            }

            override fun onResponse(call: Call<Tweet>?, response: Response<Tweet>?) {
                if( response != null ){
                    getEntity.onSuccess(response.body())
                } else {
                    getEntity.onError("Unknown Error")
                }
            }

        })
    }
}