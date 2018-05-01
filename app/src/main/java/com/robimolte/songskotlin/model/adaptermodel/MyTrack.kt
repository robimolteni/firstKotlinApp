package com.robimolte.songskotlin.model.adaptermodel

import android.os.Parcel
import android.os.Parcelable

class MyTrack(val trackId: Int, val trackName: String, val artistName: String, val coverImageURL: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(trackId)
        parcel.writeString(trackName)
        parcel.writeString(artistName)
        parcel.writeString(coverImageURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyTrack> {
        override fun createFromParcel(parcel: Parcel): MyTrack {
            return MyTrack(parcel)
        }

        override fun newArray(size: Int): Array<MyTrack?> {
            return arrayOfNulls(size)
        }
    }
}