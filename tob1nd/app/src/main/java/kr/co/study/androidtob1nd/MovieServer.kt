package kr.co.study.androidtob1nd

import retrofit2.Retrofit

object MovieServer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.kobis.or.kr/kobisopenapi/")
        .addConverterFactory()
        .build()

    val movieService = retrofit.create(MovieInterface::class.java)
}