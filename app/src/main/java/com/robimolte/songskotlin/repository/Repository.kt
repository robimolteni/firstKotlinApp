package com.robimolte.songskotlin.repository

import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import io.reactivex.Observable

interface Repository {

    fun getTrackList(apikey: String, page: Int, page_size: Int, country: String, f_has_lyrics: Int) : Observable<Message<TrackList>>
    fun getTrackDetail(apikey: String, track_id: Int) : Observable<Message<Lyrics>>

}