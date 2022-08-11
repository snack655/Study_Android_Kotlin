package fastcampus.aop.part4.chapter02.service

import retrofit2.Call
import retrofit2.http.GET

interface MusicService {

    @GET("/v3/eb3a29fa-685d-4f19-8bcf-5514715f802b")
    fun listMusics() : Call<MusicDto>
}