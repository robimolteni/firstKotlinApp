package com.robimolte.songskotlin.module

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(var mApplication: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return mApplication
    }
}