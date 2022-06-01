package fastcampus.aop.part3.aop_part3_chapter02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private val viewPager: ViewPager2 by lazy {
        findViewById(R.id.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initViews() {
        viewPager.adapter = QuotesPagerAdapter(emptyList())
    }
}