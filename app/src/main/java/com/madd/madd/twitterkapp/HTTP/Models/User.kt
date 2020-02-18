package com.madd.madd.twitterkapp.HTTP.Models

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("profile_image_url_https")
    var photo: String? = null
    @SerializedName("profile_background_image_url_https")
    var backPhoto: String? = null
}