package com.robimolte.songskotlin.mvp.all

import com.robimolte.songskotlin.model.Message
import com.robimolte.songskotlin.model.TrackList
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.ArrayList

class TrackListPresenter(var mModel : TrackListMVPInterface.Model) : TrackListMVPInterface.Presenter {

    lateinit var mView : TrackListMVPInterface.View
    lateinit var disposable: Disposable

    override fun getTrackList(page: Int) {

        mModel.getTrackList(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object: Observer<Message<TrackList>>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onError(e: Throwable) {
                //if some error happens in our data layer our app will not crash, we will
                // get error here
            }

            override fun onNext(data: Message<TrackList>) {
                if(mView != null) {
                    if (data != null) {
                        if(!(data.message.header.status_code == "200")) {

                        } else {
                            //separate the bean from backend to what I need to show on UI
                            var myTrackList = arrayListOf<MyTrack>()
                            for(t in data.message.body.track_list) {
                                val singleTrack = t.track
                                val myTrack = MyTrack(singleTrack.track_id, singleTrack.track_name, singleTrack.artist_name, singleTrack.album_coverart_100x100)
                                myTrackList.add(myTrack)
                            }
                            mView.showTrackList(myTrackList)
                        }
                    }
                }
            }

            override fun onComplete() {
            }
        })

    }

    override fun rxUnsubscribe() {
        disposable.dispose()
    }

    override fun setView(view: TrackListMVPInterface.View) {
        mView = view
    }

}