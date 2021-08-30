package kr.co.study.androidtob1nd

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("movie/searchMovieList.json")
    fun getMovieList(@Query("key") key : String): Call<MovieResponse>
}