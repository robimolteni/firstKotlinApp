package com.robimolte.songskotlin.mvp.all.adapter

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.robimolte.songskotlin.R
import com.robimolte.songskotlin.model.adaptermodel.MyTrack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.track_single_item.view.*

class TrackListAdapter(var mContext: Context, var trackList: List<MyTrack>, val clickListener: (MyTrack) -> Unit) : RecyclerView.Adapter<TrackListAdapter.TrackListViewHolder>() {


    override fun onBindViewHolder(holder: TrackListViewHolder, position: Int) {
        val itemTrack = trackList.get(position)
        holder.bindTrack(itemTrack, clickListener)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackListViewHolder{
        val inflatedView = parent.inflate(R.layout.track_single_item, false)
        return TrackListViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    inner class TrackListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var trackName: TextView
        var artistName: TextView
        var coverImage: ImageView

        init {
            trackName = view.findViewById(R.id.trackName) as TextView
            artistName = view.findViewById(R.id.artistName) as TextView
            coverImage = view.findViewById(R.id.coverImage) as ImageView
        }

        fun bindTrack(myTrack: MyTrack, clickListener: (MyTrack) -> Unit) {
            Picasso.with(view.context).load(myTrack.coverImageURL).into(view.coverImage)
            view.trackName.text = myTrack.trackName
            view.artistName.text = myTrack.artistName
            view.setOnClickListener {clickListener(myTrack)}
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}