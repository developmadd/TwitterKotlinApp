package com.madd.madd.twitterkapp.UI.Fragments.TweetDetail

import dagger.Module
import dagger.Provides

@Module
class TweetDetailModule {

    @Provides
    internal fun providePresenter(): TweetDetailContract.Presenter {
        return TweetDetailPresenter()
    }



}