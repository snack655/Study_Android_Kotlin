package kr.co.study.data

interface GithubService {

    @GET("users/{owner}/repos")
    suspend fun getRepos(@Path("owner") owner: String) : List<GithubRepoRes>
}