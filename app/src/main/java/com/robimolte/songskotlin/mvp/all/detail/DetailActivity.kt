package com.robimolte.songskotlin.mvp.all.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import com.robimolte.songskotlin.App
import com.robimolte.songskotlin.R
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import com.robimolte.songskotlin.mvp.all.TrackListMVPInterface
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.zip.GZIPOutputStream
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailMVPInterface.View {

    @Inject
    lateinit var mPresenter: DetailMVPInterface.Presenter
    lateinit var myTrack : MyTrack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (application as App).getMVPComponent().injectDetailActivity(this)
        myTrack = intent.getParcelableExtra("track") as MyTrack
        mPresenter.setView(this)
        mPresenter.getTrackDetail(myTrack.trackId)
    }

    override fun showTrackDetail(lyrics: String) {
        if(progressBar != null){
            progressBar.visibility = GONE
        }
        trackName.text = myTrack.trackName
        Picasso.with(this).load(myTrack.coverImageURL).into(coverImage)
        artistName.setText(myTrack.artistName)
        lyricsTrack.text = lyrics
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
