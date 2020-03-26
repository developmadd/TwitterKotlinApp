package com.madd.madd.twitterkapp.ui.UserProfile

import com.madd.madd.twitterkapp.http.API
import dagger.Module
import dagger.Provides

@Module
class UserProfileModule {

    @Provides
    internal fun providePresenter(model: UserProfileContract.Model): UserProfileContract.Presenter {
        return UserProfilePresenter(model)
    }

    @Provides
    internal fun provideModel(api: API): UserProfileContract.Model {
        return UserProfileModel(api)
    }

}