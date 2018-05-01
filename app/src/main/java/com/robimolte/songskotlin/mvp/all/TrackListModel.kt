package com.robimolte.songskotlin.mvp.all

import com.robimolte.songskotlin.Utils
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import com.robimolte.songskotlin.repository.Repository
import io.reactivex.Observable

class TrackListModel(var repository : Repository) : TrackListMVPInterface.Model {

    override fun getTrackList(page: Int): Observable<Message<TrackList>> {
        return  repository.getTrackList(Utils.API_KEY,page,Utils.numberOfItemPerPage,"it",1)
    }

}