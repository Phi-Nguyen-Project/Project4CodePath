package com.example.project4codepath

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray


@Parcelize

class People(
    val peopleID: Int,
    private val profilePath: String,
    // private val posterPath: String,
    // val title: String,
    // val overview: String,
    val name: String,
) :Parcelable {
    @IgnoredOnParcel
    val profileImageURL = "https://image.tmdb.org/t/p/w200/$profilePath"


    companion object{
        fun fromJsonArray(peopleJSONArray: JSONArray): List<People> {
            val people = mutableListOf<People>()
            for(i in 0 until peopleJSONArray.length()) {
                val peopleJson = peopleJSONArray.getJSONObject(i)
                people.add(
                    People(
                        peopleJson.getInt("id"),
                        peopleJson.getString("profile_path"),
                        peopleJson.getString("name")
                    )
                )

            }
        return people
        }
    }

}