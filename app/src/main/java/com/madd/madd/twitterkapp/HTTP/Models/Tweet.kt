package com.madd.madd.twitterkapp.HTTP.Models

import com.google.gson.annotations.SerializedName

class Tweet {


    @SerializedName("created_at")
    var date:String? = null
    @SerializedName("text")
    var description:String? = null
    @SerializedName("user")
    var user:User? = null
    @SerializedName("entities")
    var entities: Entity? = null

    inner class Entity {

        @SerializedName("media")
        var media: List<Media>? = null

        inner class Media {

            @SerializedName("media_url_https")
            var photo: String? = null

        }

    }



}