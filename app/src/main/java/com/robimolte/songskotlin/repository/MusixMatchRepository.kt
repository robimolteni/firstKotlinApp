package com.robimolte.songskotlin.repository

import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import io.reactivex.Observable

class MusixMatchRepository(val musixMatchAPI : MusixMatchAPI) : Repository {

    override fun getTrackDetail(apikey: String, track_id: Int): Observable<Message<Lyrics>> {
        return musixMatchAPI.getTrackDetail(apikey,track_id)
    }

    override fun getTrackList(apikey: String, page: Int, page_size: Int, country: String, f_has_lyrics: Int): Observable<Message<TrackList>> {
        return musixMatchAPI.getTrackList(apikey, page, page_size, country, f_has_lyrics)
    }
}