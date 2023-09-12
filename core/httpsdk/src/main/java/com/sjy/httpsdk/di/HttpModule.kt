package com.sjy.httpsdk.di

import android.app.Application
import com.sjy.httpsdk.adapter.ExceptionCallAdapterFactory
import com.sjy.httpsdk.api.CatFactApi
import com.sjy.httpsdk.api.CatFactSandwichApi
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

@Module
@InstallIn(SingletonComponent::class)
object HttpModule {

    @Provides
    @Singleton
    fun provideCatFactApi(
        app: Application
    ): CatFactApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 添加 Gson 转换器，如果你使用 Gson 作为序列化器
            .addCallAdapterFactory(ExceptionCallAdapterFactory())
            .build()

        return retrofit.create(CatFactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatFactSandwichApi(
        app: Application
    ): CatFactSandwichApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja") // 设置基础 URL
            .addConverterFactory(GsonConverterFactory.create()) // 添加 Gson 转换器，如果你使用 Gson 作为序列化器
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()

        return retrofit.create(CatFactSandwichApi::class.java)
    }
}