package kr.co.study.samplecleanarchitecture

import kr.co.study.data.GithubRepositoryImpl
import kr.co.study.domain.GithubRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}