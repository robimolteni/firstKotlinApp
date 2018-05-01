package com.robimolte.songskotlin.mvp.all.detail

import com.robimolte.songskotlin.model.Lyrics
import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(val mModel : DetailMVPInterface.Model) : DetailMVPInterface.Presenter {

    lateinit var mView : DetailMVPInterface.View
    lateinit var disposable: Disposable

    override fun getTrackDetail(track_id: Int) {
        mModel.getTrackDetail(track_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Message<Lyrics>> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onError(e: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onNext(t: Message<Lyrics>) {
                if(mView != null) {
                    if (t != null) {
                        if(!(t.message.header.status_code == "200")) {

                        } else {
                            //separate the bean from backend to what I need to show on UI
                            val trackLyrics = t.message.body.lyrics.lyrics_body
                            mView.showTrackDetail(trackLyrics)
                        }
                    }
                }            }

            override fun onComplete() {
            }
        })
    }

    override fun rxUnsubscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setView(view: DetailMVPInterface.View) {
        mView = view
    }

}