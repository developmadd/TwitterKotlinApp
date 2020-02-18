package com.madd.madd.twitterkapp.HTTP.Models

import com.google.gson.annotations.SerializedName

class TweetSearch {

    @SerializedName("statuses")
    var tweetList:ArrayList<Tweet>? = null

}