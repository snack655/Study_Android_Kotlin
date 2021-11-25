package kr.co.study.data

import kr.co.study.domain.GithubRepo
import kr.co.study.domain.GithubRepository

class GithubRepositoryImpl @Inject constructor(
    private val githubRemoteSource: GithubRemoteSource
) : GithubRepository {
    override suspend fun getRepos(owner: String): List<GithubRepo> {
        return githubRemoteSource.getRepos(owner)
    }
}