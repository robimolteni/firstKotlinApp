package com.robimolte.songskotlin

import android.app.Application
import com.robimolte.songskotlin.component.DaggerMVPComponent
import com.robimolte.songskotlin.component.MVPComponent
import com.robimolte.songskotlin.module.AppModule
import com.robimolte.songskotlin.module.DetailMVPModule
import com.robimolte.songskotlin.module.NetModule
import com.robimolte.songskotlin.module.TrackListMVPModule

class App : Application() {

    lateinit var component : MVPComponent
    override fun onCreate() {
        super.onCreate()
        component = DaggerMVPComponent.builder()
                .appModule(AppModule(this))
                .trackListMVPModule(TrackListMVPModule())
                .detailMVPModule(DetailMVPModule())
                .netModule(NetModule())
                .build();
    }

    fun getMVPComponent() : MVPComponent {
        return component
    }
}