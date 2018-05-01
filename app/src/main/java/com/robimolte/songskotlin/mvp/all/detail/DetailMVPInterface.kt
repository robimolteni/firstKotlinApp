package com.robimolte.songskotlin.mvp.all.detail

import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import io.reactivex.Observable

interface DetailMVPInterface {

    interface View {
        fun showTrackDetail(lyrics : String)
        fun showError()
    }

    interface Presenter {
        fun getTrackDetail(track_id : Int)
        fun rxUnsubscribe()
        fun setView(view : DetailMVPInterface.View)
    }

    interface Model {
        fun getTrackDetail(id : Int) : Observable<Message<Lyrics>>
    }
}