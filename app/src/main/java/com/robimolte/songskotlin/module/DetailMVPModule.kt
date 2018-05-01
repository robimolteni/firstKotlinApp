package com.robimolte.songskotlin.module

import com.robimolte.songskotlin.mvp.all.TrackListMVPInterface
import com.robimolte.songskotlin.mvp.all.TrackListModel
import com.robimolte.songskotlin.mvp.all.TrackListPresenter
import com.robimolte.songskotlin.mvp.all.detail.DetailMVPInterface
import com.robimolte.songskotlin.mvp.all.detail.DetailModel
import com.robimolte.songskotlin.mvp.all.detail.DetailPresenter
import com.robimolte.songskotlin.repository.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetModule::class))
class DetailMVPModule {

    @Provides
    fun provideActivityPresenter(mainModel: DetailMVPInterface.Model): DetailMVPInterface.Presenter {
        return DetailPresenter(mainModel)
    }

    @Provides
    fun provideActivityModel(repository: Repository): DetailMVPInterface.Model {
        return DetailModel(repository)
    }

}