package com.madd.madd.twitterkapp.http.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User : Serializable {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("profile_image_url_https")
    var photo: String? = null
    @SerializedName("profile_background_image_url_https")
    var backPhoto: String? = null
}