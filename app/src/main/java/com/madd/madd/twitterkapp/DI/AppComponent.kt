package com.madd.madd.twitterkapp.DI

import com.madd.madd.twitterkapp.HTTP.APIModule
import com.madd.madd.twitterkapp.UI.Activities.MainActivity
import com.madd.madd.twitterkapp.UI.Fragments.TweetDetail.TweetDetailFragment
import com.madd.madd.twitterkapp.UI.Fragments.TweetDetail.TweetDetailModule
import com.madd.madd.twitterkapp.UI.Fragments.TweetRegister.TweetRegisterFragment
import com.madd.madd.twitterkapp.UI.Fragments.TweetRegister.TweetRegisterModule
import com.madd.madd.twitterkapp.UI.Fragments.TweetSearch.TweetSearchFragment
import com.madd.madd.twitterkapp.UI.Fragments.TweetSearch.TweetSearchModule
import com.madd.madd.twitterkapp.UI.Fragments.UserProfile.UserProfileFragment
import com.madd.madd.twitterkapp.UI.Fragments.UserProfile.UserProfileModule
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

    fun inject(target: MainActivity)
    fun inject(target: UserProfileFragment)
    fun inject(target: TweetSearchFragment)
    fun inject(target: TweetRegisterFragment)
    fun inject(target: TweetDetailFragment)

}