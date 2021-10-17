package com.elmorshdi.movieexplorer.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


 data class Movies(
    var errorMessage: String?,
    var items: List<Item>?
)
data class Item(
    var contentRating: String?,
    var directorList: List<Director>?,
    var directors: String?,
    var fullTitle: String?,
    var genreList: List<Genre>?,
    var genres: String?,
    var id: String?,
    var imDbRating: String?,
    var imDbRatingCount: String?,
    var image: String?,
    var metacriticRating: String?,
    var plot: String?,
    var releaseState: String?,
    var runtimeMins: String?,
    var runtimeStr: String?,
    var starList: List<Star>?,
    var stars: String?,
    var title: String?,
    var year: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("directorList"),
        parcel.readString(),
        parcel.readString(),
        TODO("genreList"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        TODO("starList"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contentRating)
        parcel.writeString(directors)
        parcel.writeString(fullTitle)
        parcel.writeString(genres)
        parcel.writeString(id)
        parcel.writeString(imDbRating)
        parcel.writeString(imDbRatingCount)
        parcel.writeString(image)
        parcel.writeString(metacriticRating)
        parcel.writeString(plot)
        parcel.writeString(releaseState)
        parcel.writeString(runtimeMins)
        parcel.writeString(runtimeStr)
        parcel.writeString(stars)
        parcel.writeString(title)
        parcel.writeString(year)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}

data class Director(
    var id: String?,
    var name: String?
)
data class Genre(
    var key: String?,
    var value: String?
)
data class Star(
    var id: String?,
    var name: String?
)