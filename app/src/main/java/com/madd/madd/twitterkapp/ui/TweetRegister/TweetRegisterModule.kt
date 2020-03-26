package com.madd.madd.twitterkapp.ui.TweetRegister

import com.madd.madd.twitterkapp.http.API
import dagger.Module
import dagger.Provides

@Module
class TweetRegisterModule {

    @Provides
    internal fun providePresenter(model: TweetRegisterContract.Model): TweetRegisterContract.Presenter {
        return TweetRegisterPresenter(model)
    }

    @Provides
    internal fun provideModel(api: API): TweetRegisterContract.Model {
        return TweetRegisterModel(api)
    }

}