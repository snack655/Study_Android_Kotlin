package kr.co.hanbit.studyintentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.co.hanbit.studyintentkt.databinding.ActivityMainBinding
import kr.co.hanbit.studyintentkt.databinding.ActivitySubBinding

class SubActivity : AppCompatActivity() {

    val binding by lazy { ActivitySubBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.to1.text = intent.getStringExtra("from1")
        binding.to2.text = "${intent.getIntExtra("from2", 0)}"

        binding.btnClose.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("returnValue", binding.editMessage.text.toString())
            setResult(RESULT_OK, returnIntent)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RESULT_OK) {
            when (requestCode) {
                95 -> {
                    val YestoIdMessage = data?.getStringExtra("thisisId")
                    val YestoPwMessage = data?.getStringExtra("thisisPassword")
                    Toast.makeText(this, YestoIdMessage, Toast.LENGTH_LONG).show()
                    Toast.makeText(this, YestoPwMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}