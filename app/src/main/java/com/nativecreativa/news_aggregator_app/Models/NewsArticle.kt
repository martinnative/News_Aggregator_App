package com.nativecreativa.news_aggregator_app.Models

import android.os.Parcel
import android.os.Parcelable

data class NewsArticle(
    val title: String,
    val author: String?,
    val description: String?,
    val urlToImage: String?,
    val url: String,
    val publishedAt: String,
    val content: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeString(description)
        parcel.writeString(urlToImage)
        parcel.writeString(url)
        parcel.writeString(publishedAt)
        parcel.writeString(content)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NewsArticle> {
        override fun createFromParcel(parcel: Parcel): NewsArticle {
            return NewsArticle(parcel)
        }

        override fun newArray(size: Int): Array<NewsArticle?> {
            return arrayOfNulls(size)
        }
    }
}
