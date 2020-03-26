package com.madd.madd.twitterkapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Module
class AppModule(var app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

}