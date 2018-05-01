package com.robimolte.songskotlin.mvp.all

import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import io.reactivex.Observable

interface TrackListMVPInterface {

    interface View {
        fun showTrackList(track : List<MyTrack>)
        fun showError()
    }

    interface Presenter {
        fun getTrackList(page: Int)
        fun rxUnsubscribe()
        fun setView(view : TrackListMVPInterface.View)
    }

    interface Model {
        fun getTrackList(page : Int) : Observable<Message<TrackList>>
    }
}