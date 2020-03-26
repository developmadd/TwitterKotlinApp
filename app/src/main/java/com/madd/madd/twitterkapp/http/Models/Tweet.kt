package com.madd.madd.twitterkapp.http.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Tweet : Serializable{


    @SerializedName("created_at")
    var date:String? = null
    @SerializedName("text")
    var description:String? = null
    @SerializedName("user")
    var user:User? = null
    @SerializedName("entities")
    var entities: Entity? = null

    inner class Entity : Serializable{

        @SerializedName("media")
        var media: List<Media>? = null

        inner class Media : Serializable{

            @SerializedName("media_url_https")
            var photo: String? = null

        }

    }



}