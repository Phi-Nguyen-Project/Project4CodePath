package com.example.project4codepath

import android.support.annotation.Keep
import com.google.gson.JsonParser
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Keep
@Serializable
data class GetNewResult (
    @SerialName("results")
    val result: List<People>?
)

@Keep
@Serializable
data class People(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val profilePath: String,
    @SerialName("known_for")
    var movieKnownfor: List<MovieKnownfor>?

): java.io.Serializable{
    val profileImageURL = "https://image.tmdb.org/t/p/w200/$profilePath"
    // <T> List<T>.firstOrNull(): T?

}


@Keep
@Serializable
data class MovieKnownfor(
    @SerialName("title")
    val title: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null
) : java.io.Serializable {
    val posterImageURL = "https://image.tmdb.org/t/p/w200/$posterPath"
}





/*
@Keep
@Serializab
data class GetNewResult (
    @SerialName("results")
    val result: List<People>?
)

@Keep
@Serializable
data class People(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val profilePath: String

): java.io.Serializable{
    val profileImageURL = "https://image.tmdb.org/t/p/w200/$profilePath"
}

*/













