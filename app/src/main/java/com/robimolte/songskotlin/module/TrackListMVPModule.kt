package com.robimolte.songskotlin.module

import com.robimolte.songskotlin.mvp.all.TrackListMVPInterface
import com.robimolte.songskotlin.mvp.all.TrackListModel
import com.robimolte.songskotlin.mvp.all.TrackListPresenter
import com.robimolte.songskotlin.repository.MusixMatchAPI
import com.robimolte.songskotlin.repository.MusixMatchRepository
import com.robimolte.songskotlin.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetModule::class))
class TrackListMVPModule {

    @Provides
    fun provideActivityPresenter(mainModel: TrackListMVPInterface.Model): TrackListMVPInterface.Presenter {
        return TrackListPresenter(mainModel)
    }

    @Provides
    fun provideActivityModel(repository: Repository): TrackListMVPInterface.Model {
        return TrackListModel(repository)
    }

    @Singleton
    @Provides
    fun provideRepo(musixMatchApiService: MusixMatchAPI): Repository {
        return MusixMatchRepository(musixMatchApiService)
    }
}