package com.ngedev.movieexpert.core.di

import com.ngedev.movieexpert.core.BuildConfig
import com.ngedev.movieexpert.core.data.network.InterfaceApi
import com.ngedev.movieexpert.core.util.Cons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val pinner = CertificatePinner.Builder()
            .add(Cons.BASE_API_URL, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .add(Cons.BASE_API_URL, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(Cons.BASE_API_URL, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(pinner)

        if(BuildConfig.DEBUG){
            httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return httpClient.build()


    }

    @Provides
    fun provideInterfaceApi(client: OkHttpClient): InterfaceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Cons.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(InterfaceApi::class.java)
    }
}