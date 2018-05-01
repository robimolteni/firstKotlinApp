package com.robimolte.songskotlin.component

import com.robimolte.songskotlin.module.AppModule
import com.robimolte.songskotlin.module.DetailMVPModule
import com.robimolte.songskotlin.module.NetModule
import com.robimolte.songskotlin.module.TrackListMVPModule
import com.robimolte.songskotlin.mvp.all.MainActivity
import com.robimolte.songskotlin.mvp.all.detail.DetailActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(TrackListMVPModule::class, AppModule::class, NetModule::class, DetailMVPModule::class))
interface MVPComponent {
    fun injectMainActivity(activity : MainActivity)
    fun injectDetailActivity(activity: DetailActivity)
}