package com.madd.madd.twitterkapp.di


import android.app.Application
import com.madd.madd.twitterkapp.http.APIModule
import com.madd.madd.twitterkapp.ui.TweetDetail.TweetDetailModule
import com.madd.madd.twitterkapp.ui.TweetRegister.TweetRegisterModule
import com.madd.madd.twitterkapp.ui.TweetSearch.TweetSearchModule
import com.madd.madd.twitterkapp.ui.UserProfile.UserProfileModule

class App: Application() {

    var component:AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .aPIModule(APIModule())
            .userProfileModule(UserProfileModule())
            .tweetSearchModule(TweetSearchModule())
            .tweetRegisterModule(TweetRegisterModule())
            .tweetDetailModule(TweetDetailModule())
            .build()
    }


}