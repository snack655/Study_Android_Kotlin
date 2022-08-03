package fastcampus.aop.part4.chapter01.service

import fastcampus.aop.part4.chapter01.dto.VideoDto
import retrofit2.Call
import retrofit2.http.GET

interface VideoService {
    @GET("/v3/ca85fd80-770b-4b23-b8e7-21fecf146261")
    fun listVideos(): Call<VideoDto>
}