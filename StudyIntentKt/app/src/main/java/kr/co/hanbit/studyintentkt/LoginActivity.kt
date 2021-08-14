package kr.co.hanbit.studyintentkt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.hanbit.studyintentkt.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val ConnectMessage = intent.getStringExtra("fromMain")



        binding.BackToMain.setOnClickListener {
            val returnMainIntent = Intent()
            returnMainIntent.putExtra("returnToMain", binding.editWhyBack.text.toString())
            setResult(RESULT_OK, returnMainIntent)
            finish()
        }

        val YesLoginIntent = Intent()
        YesLoginIntent.putExtra("thisisId", binding.editId.text.toString())
        YesLoginIntent.putExtra("thisisPassword", binding.editPw.text.toString())
        binding.btnLogin.setOnClickListener { startActivityForResult(YesLoginIntent, 95) }
    }
}