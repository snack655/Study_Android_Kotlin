package kr.co.study.domain

interface GithubRepository {
    suspend fun getRepos(owner: String): List<GithubRepo>
}