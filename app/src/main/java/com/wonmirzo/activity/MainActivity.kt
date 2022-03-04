package com.wonmirzo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.wonmirzo.R
import com.wonmirzo.adapter.PostAdapter
import com.wonmirzo.model.Poster
import com.wonmirzo.model.PosterResp
import com.wonmirzo.network.retrofit.RetrofitHttp
import com.wonmirzo.network.volley.VolleyHandler
import com.wonmirzo.network.volley.VolleyHttp
import com.wonmirzo.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    var posters = ArrayList<Poster>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        val btnFloating: FloatingActionButton = findViewById(R.id.btnFloating)

        apiPosterList()
    }

    fun refreshAdapter(posters: ArrayList<Poster>) {
        val adapter = PostAdapter(this, posters)
        recyclerView.adapter = adapter
    }

    fun dialogPoster(poster: Poster?) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setPositiveButton(android.R.string.yes) {
                dialog, which -> apiPosterDelete(poster!!)
            }
            .setNegativeButton(android.R.string.no, null)
            .show()
    }

    private fun apiPosterList() {
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                val postArray = Gson().fromJson(response, Array<Poster>::class.java)
                posters.addAll(postArray)

                refreshAdapter(posters)
                Logger.d("@@@onResponse ", "" + posters.size)
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }
        })
    }

    private fun apiPosterDelete(poster: Poster) {
        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("@@@", response!!)
            }

            override fun onError(error: String?) {
                Logger.d("@@@", error!!)
            }
        })
    }
}