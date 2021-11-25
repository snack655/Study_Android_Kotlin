package kr.co.study.samplecleanarchitecture

import android.app.Application
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    private const val CONNECT_TIMEOUT = 15L
    private const val WRITE_TIMEOUT = 15L
    private const val READ_TIMEOUT = 15L
    private const val BASE_URL = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache{
        return Cache(application.cacheDir, 10L * 1024 * 1024)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Lever.BODY
            })
        }.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideDeliveryService(retrofit: Retrofit): GithubService {
        return retrofit.create(GithubService::class.java)
    }
}