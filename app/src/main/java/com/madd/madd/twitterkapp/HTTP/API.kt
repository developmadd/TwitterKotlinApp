package com.madd.madd.twitterkapp.HTTP

import com.madd.madd.twitterkapp.HTTP.Models.PostTweet
import com.madd.madd.twitterkapp.HTTP.Models.Tweet
import com.madd.madd.twitterkapp.HTTP.Models.TweetSearch
import com.madd.madd.twitterkapp.HTTP.Models.User
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

