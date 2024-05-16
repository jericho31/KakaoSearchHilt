package com.example.kakaosearchhilt.di

import android.content.Context
import com.example.kakaosearchhilt.BuildConfig
import com.example.kakaosearchhilt.data.ImageSearchRepository
import com.example.kakaosearchhilt.data.NetworkImageSearchRepository
import com.example.kakaosearchhilt.network.KakaoImageApiService
import com.example.kakaosearchhilt.pref.PrefSearchKeywordRepository
import com.example.kakaosearchhilt.pref.PrefStringRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyModule {

    @Provides
    fun provideBaseUrl() = "https://dapi.kakao.com/"

    @Provides
    @Singleton
    fun provideCreateOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder().also {
            it.connectTimeout(20, TimeUnit.SECONDS)
            it.readTimeout(20, TimeUnit.SECONDS)
            it.writeTimeout(20, TimeUnit.SECONDS)
            it.addNetworkInterceptor(interceptor)

        }.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    /** Use the Retrofit builder to build a retrofit object using a MoshiConverterFactory */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl(provideBaseUrl())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()

    /** Retrofit service object for creating api calls */
    @Provides
    @Singleton
    fun provideRetrofitService(retrofit: Retrofit): KakaoImageApiService =
        retrofit.create(KakaoImageApiService::class.java)

    /** DI implementation for repository */
    @Provides
    @Singleton
    fun provideImageSearchRepository(retrofitService: KakaoImageApiService): ImageSearchRepository =
        NetworkImageSearchRepository(retrofitService)

    //-----------------

    @Provides
    @Singleton
    fun providePrefSearchKeywordRepository(@ApplicationContext appContext: Context): PrefSearchKeywordRepository =
        PrefStringRepositoryImpl(appContext)

}
