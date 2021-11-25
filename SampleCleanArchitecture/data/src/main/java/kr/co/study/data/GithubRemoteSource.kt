package kr.co.study.data

interface GithubRemoteSource {
    suspend fun getRepos(owner: String): List<GithubRepoRes>
}

class GithubRemoteSourceImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRemoteSource {
    override suspend fun getRepos(owner: String): List<GithubRepoRes> {
        return githubService.getRepos(owner)
    }
}