package com.wonmirzo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.wonmirzo.R
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
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
//        workWithVolley()
        workWithRetrofit()
        textView = findViewById(R.id.textView)
    }

    private fun workWithVolley() {
        /* VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
             override fun onSuccess(response: String?) {
                 Logger.d("VolleyHttp", response!!)
                 textView.text = response
             }

             override fun onError(error: String?) {
                 Logger.d("VolleyHttp", error!!)
             }
         })*/

        val poster = Poster(1, 1, "PDP", "Online")
        VolleyHttp.post(VolleyHttp.API_CREATE_POST, VolleyHttp.paramsCreate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Logger.d("VolleyHttp", response!!)
                    textView.text = response
                }

                override fun onError(error: String?) {
                    Logger.d("VolleyHttp", error!!)
                }
            })

        /* VolleyHttp.put(VolleyHttp.API_UPDATE_POST + poster.id, VolleyHttp.paramsUpdate(poster),
         object : VolleyHandler {
             override fun onSuccess(response: String?) {
                 Logger.d("VolleyHttp", response!!)
                 textView.text = response
             }

             override fun onError(error: String?) {
                 Logger.d("VolleyHttp", error!!)
             }
         })*/

        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                textView.text = response
                Logger.d("VolleyHttp", response!!)
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
            }
        })

    }

    private fun workWithRetrofit() {

        /*RetrofitHttp.posterService.listPost().enqueue(object : Callback<ArrayList<PosterResp>> {
            override fun onResponse(
                call: Call<ArrayList<PosterResp>>,
                response: Response<ArrayList<PosterResp>>
            ) {
                Logger.d("RetrofitHttp", response.body().toString())
                textView.text = response.body().toString()
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Logger.d("RetrofitHttp", t.message.toString())
            }
        })*/

        val poster = Poster(1, 1, "PDP", "Online")

        /*RetrofitHttp.posterService.createPost(poster).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                textView.text = response.body().toString()
                Logger.d("RetrofitHttp", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("RetrofitHttp", t.message.toString())
            }
        })*/

        /* RetrofitHttp.posterService.updatePost(poster.id, poster)
             .enqueue(object : Callback<PosterResp> {
                 override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                     textView.text = response.body().toString()
                     Logger.d("RetrofitHttp", response.body().toString())
                 }

                 override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                     Logger.d("RetrofitHttp", t.message.toString())
                 }
             })*/

        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                textView.text = response.body().toString()
                Logger.d("RetrofitHttp", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.d("RetrofitHttp", t.message.toString())
            }
        })
    }
}