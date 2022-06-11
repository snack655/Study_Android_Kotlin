package fastcampus.aop.part3.chapter04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fastcampus.aop.part3.chapter04.api.BookService
import fastcampus.aop.part3.chapter04.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpak.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("21358A48D3C0AD1539B62F9FAA8B1E644283E310683F79CFE9D3D21C2E02B9B2")
            .enqueue(object : Callback<BestSellerDto> {
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT!! SUCCESS", )
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    Log.e(TAG, t.toString(), )
                }

            })
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}