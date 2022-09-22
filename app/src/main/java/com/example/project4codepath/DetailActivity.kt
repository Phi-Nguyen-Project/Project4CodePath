package com.example.project4codepath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var peopleImageView: ImageView
    private lateinit var posterImageView: ImageView
    private lateinit var peopleName: TextView
    private lateinit var movieTittle: TextView
    private lateinit var overView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find 3tv and 2 iv
        peopleImageView = findViewById(R.id.ivPeopleDetail)
        posterImageView = findViewById(R.id.ivMovie)
        peopleName  = findViewById(R.id.tvNameDetail)
        movieTittle = findViewById(R.id.tvKnownFor)
        overView = findViewById(R.id.tvOverview)

        // Get EXTRA components from extra intent
        val people = intent.getSerializableExtra(PEOPLE_EXTRA) as People

        // Populate new data from extra to name, movie name, and overview
        peopleName.text = people.name
        movieTittle.text = people.movieKnownfor?.get(0)?.title
        overView.text = people.movieKnownfor?.get(0)?.overview

        Glide.with(this)
            .load(people.profileImageURL)
            .into(peopleImageView)

        Glide.with(this)
            .load(people.movieKnownfor?.get(0)?.posterImageURL)
            .into(posterImageView)

    }
}