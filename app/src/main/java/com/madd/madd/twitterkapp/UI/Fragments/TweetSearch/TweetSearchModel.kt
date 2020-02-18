package com.madd.madd.twitterkapp.UI.Fragments.TweetSearch

import com.madd.madd.twitterkapp.HTTP.API
import com.madd.madd.twitterkapp.HTTP.Models.TweetSearch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TweetSearchModel(private var api: API) : TweetSearchContract.Model {


    override fun getList(query: String, getList: TweetSearchContract.Model.GetList) {
        api.getTweetListByQuery(query).enqueue(object: Callback<TweetSearch>{
            override fun onFailure(call: Call<TweetSearch>?, t: Throwable?) {
                if(t?.message != null) {
                    getList.onError(t.message!!)
                } else {
                    getList.onError("Unknown Error")
                }
            }

            override fun onResponse(call: Call<TweetSearch>?, response: Response<TweetSearch>?) {
                if( response != null && response.body().tweetList != null ) {
                    getList.onSuccess(response.body().tweetList!!)
                } else {
                    getList.onSuccess(ArrayList())
                }
            }

        })
    }
}