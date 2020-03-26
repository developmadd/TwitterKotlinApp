package com.madd.madd.twitterkapp.http

import com.madd.madd.twitterkapp.http.Models.PostTweet
import com.madd.madd.twitterkapp.http.Models.Tweet
import com.madd.madd.twitterkapp.http.Models.TweetSearch
import com.madd.madd.twitterkapp.http.Models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface API {


    @GET("/api/user ")
    fun getUser(): Call<User>

    @GET("api/statuses/user_timeline")
    fun getUserTimeLine(): Call<List<Tweet>>


    @GET("/api/search/{query}")
    fun getTweetListByQuery(@Path("query") query: String): Call<TweetSearch>


    @GET("/api/statuses/update ")
    fun registerTweet(@Body body: PostTweet): Call<Tweet>


}

