package kr.co.study.androidtob1nd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MovieAdapter()
        findViewById<RecyclerView>(R.id.rv_movie).adapter = adapter

        val call = MovieServer.movieService.getMovieList("6d41ee9f12b481a485ff572257832326")

        call.enqueue(object: Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if(response.code() == 200) {
                    Log.d("MOVIETEST", "${response.body()}")
                    val movieList = response.body()?.movieListResult?.movieList ?: listOf()
                    adapter.itemList = movieList as ArrayList<Movie>

                    adapter.notifyDataSetChanged()
                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("MOVIETEST", "${t.message}")
            }
        })

    }
}