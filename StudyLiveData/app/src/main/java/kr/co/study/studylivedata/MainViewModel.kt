package kr.co.study.studylivedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    val count = MutableLiveData<Int>(0)
}