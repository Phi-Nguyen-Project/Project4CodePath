package com.example.project4codepath

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project4codepath.databinding.ActivityMainBinding
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity"
private const val PERSON_POPULAR = "https://api.themoviedb.org/3/person/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

class MainActivity : AppCompatActivity() {
    private val people = mutableListOf<People>()
    private lateinit var rvPeople: RecyclerView
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // call binding function
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Find recyclerview to populate data
        rvPeople = findViewById(R.id.rvPeople)

        // Set up adapter with people
        val peopleAdapter = PeopleAdapter(this, people)
        rvPeople.adapter = peopleAdapter
        rvPeople.layoutManager = GridLayoutManager(applicationContext,2)

        // Using asyncHttpClient to get data from api
        val client = AsyncHttpClient()
        client.get(PERSON_POPULAR, object: JsonHttpResponseHandler(){
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(ContentValues.TAG,"onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(ContentValues.TAG, "onSuccess: JSON data $json")

                try {
                    // Create parse json
                    val parsedJson = createJson().decodeFromString(
                        GetNewResult.serializer(),
                        json.jsonObject.toString()
                    )
                    //  Save the articles and reload the screen
                    parsedJson.result?.let { list ->
                        people.addAll(list)
                        //reload screen
                        peopleAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }

            }

        })
    }
}