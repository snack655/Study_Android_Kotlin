package kr.co.study.lottie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kr.co.study.lottie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    var isAnimating: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.send.playAnimation()

        binding.send.setOnClickListener {
            if (isAnimating) {
                binding.send.cancelAnimation()
                Toast.makeText(applicationContext, "stop", Toast.LENGTH_SHORT).show()
                isAnimating = false
            } else {
                binding.send.playAnimation()
                Toast.makeText(applicationContext, "start", Toast.LENGTH_SHORT).show()
                isAnimating = true
            }
        }
    }
}