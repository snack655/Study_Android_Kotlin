package kr.co.study.unsplash_app_tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import kr.co.study.unsplash_app_tutorial.databinding.ActivityMainBinding
import kr.co.study.unsplash_app_tutorial.utils.Constants.TAG
import kr.co.study.unsplash_app_tutorial.utils.SEARCH_TYPE
import kr.co.study.unsplash_app_tutorial.utils.onMyTextChanged

class MainActivity : AppCompatActivity() {

    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater) // 1
        setContentView(binding.root) // 2

        Log.d(TAG, "MainActivity - onCreate() called")

        // 라디오 그룹 가져오기
        binding.searchTermRadioGroup.setOnCheckedChangeListener { _, checkedId ->

            when(checkedId) {
                R.id.photo_search_radio_btn -> {
                    Log.d(TAG, "사진검색 버튼 클릭!")
                    binding.searchTermTextLayout.hint = "사진검색"
                    binding.searchTermTextLayout.startIconDrawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_photo)
                    this.currentSearchType = SEARCH_TYPE.PHOTO
                }

                R.id.user_search_radio_btn -> {
                    Log.d(TAG, "사용자검색 버튼 클릭!")
                    binding.searchTermTextLayout.hint = "사용자검색"
                    binding.searchTermTextLayout.startIconDrawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_baseline_person_24)
                    this.currentSearchType = SEARCH_TYPE.USER
                }
            }
            Log.d(TAG, "MainActivity - OnCheckedChanged() called / currentSearchType : $currentSearchType")
        }

        // 텍스트가 변경이 되었을때
        binding.searchTermEditText.onMyTextChanged {
            // 입력된 글자가 하나라도 있다면
            if (it.toString().count() > 0) {
                // 검색 버튼을 보여준다.
                binding.included.frameSearchBtn.visibility = View.VISIBLE
                binding.searchTermTextLayout.helperText = " "
                // 스크롤뷰를 올린다.
                binding.mainScrollview.scrollTo(0, 200)
            } else {
                binding.included.frameSearchBtn.visibility = View.INVISIBLE
            }

            if (it.toString().count() == 12) {
                Log.d(TAG, "MainActivity - 에러 띄우기")
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 버튼 클릭시
        binding.included.btnSearch.setOnClickListener {
            Log.d(TAG, "MainActivity - 검색 버튼이 클릭되었다. / currentSearchType : $currentSearchType")
            this.handleSearchButtonUi(binding)
        }


    }// onCreate


    private fun handleSearchButtonUi(binding: ActivityMainBinding) {
        binding.included.btnProgress.visibility = View.VISIBLE
        binding.included.btnSearch.text = ""

        Handler(Looper.getMainLooper()).postDelayed({
            binding.included.btnProgress.visibility = View.INVISIBLE
            binding.included.btnSearch.text = "검색"
        }, 1500)
    }
}