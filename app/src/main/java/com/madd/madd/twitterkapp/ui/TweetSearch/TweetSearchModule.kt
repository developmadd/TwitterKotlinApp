package com.madd.madd.twitterkapp.ui.TweetSearch

import com.madd.madd.twitterkapp.http.API
import dagger.Module
import dagger.Provides

@Module
class TweetSearchModule {

    @Provides
    internal fun providePresenter(model: TweetSearchContract.Model): TweetSearchContract.Presenter {
        return TweetSearchPresenter(model)
    }

    @Provides
    internal fun provideModel(api: API): TweetSearchContract.Model {
        return TweetSearchModel(api)
    }

}