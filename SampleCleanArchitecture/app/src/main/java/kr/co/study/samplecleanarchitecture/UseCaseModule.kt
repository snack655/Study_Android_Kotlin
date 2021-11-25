package kr.co.study.samplecleanarchitecture

import kr.co.study.domain.GetGithubReposUseCase
import kr.co.study.domain.GithubRepository

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun providesGetGithubReposUseCase(repository: GithubRepository): GetGithubReposUseCase {
        return GetGithubReposUseCase(repository)
    }
}