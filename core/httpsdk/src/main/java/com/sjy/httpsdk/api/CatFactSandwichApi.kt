package com.sjy.httpsdk.api

import com.sjy.httpsdk.dto.CatFact
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */
interface CatFactSandwichApi {

    @GET("/fact")
    suspend fun getOneFact(): ApiResponse<CatFact>
}