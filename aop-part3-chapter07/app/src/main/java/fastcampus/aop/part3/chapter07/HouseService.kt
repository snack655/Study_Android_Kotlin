package fastcampus.aop.part3.chapter07

import retrofit2.Call
import retrofit2.http.GET

interface HouseService {
    @GET("/v3/b25ac27d-b581-46b7-a410-286d9b2cdfc7")
    fun getHouseList(): Call<HouseDto>
}