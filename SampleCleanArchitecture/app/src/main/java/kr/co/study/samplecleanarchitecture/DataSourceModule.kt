package kr.co.study.samplecleanarchitecture

import kr.co.study.data.GithubRemoteSource
import kr.co.study.data.GithubRemoteSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindsGithubRemoteSource(source: GithubRemoteSourceImpl): GithubRemoteSource
}