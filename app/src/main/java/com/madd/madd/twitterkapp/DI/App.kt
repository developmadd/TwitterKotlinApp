package com.madd.madd.twitterkapp.DI


import android.app.Application
import com.madd.madd.twitterkapp.HTTP.APIModule
import com.madd.madd.twitterkapp.UI.Fragments.TweetDetail.TweetDetailModule
import com.madd.madd.twitterkapp.UI.Fragments.TweetRegister.TweetRegisterModule
import com.madd.madd.twitterkapp.UI.Fragments.TweetSearch.TweetSearchModule
import com.madd.madd.twitterkapp.UI.Fragments.UserProfile.UserProfileModule

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