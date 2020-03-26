package com.madd.madd.twitterkapp.ui.TweetDetail

import dagger.Module
import dagger.Provides

@Module
class TweetDetailModule {

    @Provides
    internal fun providePresenter(): TweetDetailContract.Presenter {
        return TweetDetailPresenter()
    }



}