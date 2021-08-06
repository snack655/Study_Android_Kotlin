package kr.co.hanbit.basicsytax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var myName = "홍길동"
        var myAge: Int
        myAge = 27
        myAge += 1
        Log.d("BasicSyntex", "myName = $myName, myAge = $myAge")
    }
}