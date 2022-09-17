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
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "mainactivity"
private const val PERSON_POPULAR = "https://api.themoviedb.org/3/person/popular?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
class MainActivity : AppCompatActivity() {
    private val people = mutableListOf<People>()
    private lateinit var rvPeople: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPeople = findViewById(R.id.rvPeople)

        val peopleAdapter = PeopleAdapter(this, people)

        rvPeople.adapter = peopleAdapter
        rvPeople.layoutManager = GridLayoutManager(applicationContext,2)

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

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(ContentValues.TAG, "onSuccess: JSON data $json")

                try{
                    val peopleJsonArray = json.jsonObject.getJSONArray("results")
                    people.addAll(People.fromJsonArray(peopleJsonArray))
                    peopleAdapter.notifyDataSetChanged()
                    Log.i(ContentValues.TAG, "people list $people")
                }catch(e: JSONException){
                    Log.e(ContentValues.TAG,"Encountered exception $e")
                }

            }

        })
    }
}