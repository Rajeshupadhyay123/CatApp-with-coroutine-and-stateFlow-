package com.example.catapp.module

import com.example.catapp.network.CatApi
import com.example.catapp.network.MyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val client=OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    @Provides
    @BaseUrl
    fun providebaseUrl():String="https://api.thecatapi.com/"

    @Provides
    @Singleton
    fun provideRetrofit(@BaseUrl baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)   //for interceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):CatApi{
        return retrofit.create(CatApi::class.java)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl