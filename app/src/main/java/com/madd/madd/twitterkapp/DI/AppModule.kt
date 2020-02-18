package com.madd.madd.twitterkapp.DI

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

}