package com.madd.madd.twitterkapp.di

import com.madd.madd.twitterkapp.http.APIModule
import com.madd.madd.twitterkapp.ui.TweetDetail.TweetDetailActivity
import com.madd.madd.twitterkapp.ui.UserProfile.UserProfileActivity
import com.madd.madd.twitterkapp.ui.TweetDetail.TweetDetailModule
import com.madd.madd.twitterkapp.ui.TweetRegister.TweetRegisterActivity
import com.madd.madd.twitterkapp.ui.TweetRegister.TweetRegisterModule
import com.madd.madd.twitterkapp.ui.TweetSearch.TweetSearchActivity
import com.madd.madd.twitterkapp.ui.TweetSearch.TweetSearchModule
import com.madd.madd.twitterkapp.ui.UserProfile.UserProfileModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    AppModule::class,
    APIModule::class,
    UserProfileModule::class,
    TweetSearchModule::class,
    TweetRegisterModule::class,
    TweetDetailModule::class
])
interface AppComponent {

    fun inject(target: UserProfileActivity)
    fun inject(target: TweetSearchActivity)
    fun inject(target: TweetRegisterActivity)
    fun inject(target: TweetDetailActivity)

}