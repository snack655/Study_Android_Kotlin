package kr.co.study.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.study.domain.GetGithubReposUseCase
import kr.co.study.domain.GithubRepo

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGithubReposUseCase: GetGithubReposUseCase
): BaseViewModel() {
    private val _githubRepositories = MutableLiveData<List<GithubRepo>>()
    val githubRepositories: LiveData<List<GithubRepo>> = _githubRepositories

    fun getGithubRepositories(owner: String) {
        getGithubReposUseCase(owner, viewModelScope) {
            _githubRepositories.value = it
        }
    }
}