package com.robimolte.songskotlin.mvp.all.detail

import com.robimolte.songskotlin.Utils
import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.repository.Repository
import io.reactivex.Observable

class DetailModel(val repository: Repository) : DetailMVPInterface.Model {

    override fun getTrackDetail(id: Int): Observable<Message<Lyrics>> {
        return repository.getTrackDetail(Utils.API_KEY, id)
    }

}