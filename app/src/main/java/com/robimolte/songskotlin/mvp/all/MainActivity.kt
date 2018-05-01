package com.robimolte.songskotlin.mvp.all

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import com.robimolte.songskotlin.App
import com.robimolte.songskotlin.R
import com.robimolte.songskotlin.Utils
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import com.robimolte.songskotlin.mvp.all.adapter.TrackListAdapter
import com.robimolte.songskotlin.mvp.all.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TrackListMVPInterface.View {

    @Inject
    lateinit var mPresenter: TrackListMVPInterface.Presenter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var trackList = ArrayList<MyTrack>()
    private lateinit var adapter: TrackListAdapter
    var currentPage = 1
    var endOfList = false
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).getMVPComponent().injectMainActivity(this)
        adapter = TrackListAdapter(applicationContext, trackList, { partItem : MyTrack -> partItemClicked(partItem) })
        linearLayoutManager = LinearLayoutManager(applicationContext)
        trackListRecyclerview.layoutManager = linearLayoutManager
        trackListRecyclerview.adapter = adapter
        mPresenter.setView(this)
        mPresenter.getTrackList(1)

        trackListRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount
                val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (!isLoading && !endOfList) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                        progressBar.visibility = View.VISIBLE
                        isLoading = true
                        currentPage++
                        mPresenter.getTrackList(currentPage)
                    }
                }
            }
        })
    }

    override fun showTrackList(track: List<MyTrack>) {
        if (progressBar != null) {
            progressBar.visibility = GONE
        }
        isLoading = false
        if (track.size < Utils.numberOfItemPerPage) {
            endOfList = true
        }
        trackList.addAll(track)
        if (adapter != null) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPause() {
        super.onPause()
        mPresenter.rxUnsubscribe()
    }

    private fun partItemClicked(myTrack: MyTrack) {
        val showDetailActivityIntent = Intent(this, DetailActivity::class.java)
        showDetailActivityIntent.putExtra("track",myTrack)
        startActivity(showDetailActivityIntent)
    }
}