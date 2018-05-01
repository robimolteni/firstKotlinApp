package com.robimolte.songskotlin.repository

import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MusixMatchAPI {

    @GET("/ws/1.1/chart.tracks.get")
    fun getTrackList(
            @Query("apikey") apikey: String,
            @Query("page") page: Int,
            @Query("page_size") page_size: Int,
            @Query("country") country: String,
            @Query("f_has_lyrics") f_has_lyrics: Int): Observable<Message<TrackList>>

    @GET("/ws/1.1/track.lyrics.get")
    fun getTrackDetail(
            @Query("apikey") apikey: String,
            @Query("track_id") track_id: Int): Observable<Message<Lyrics>>
}