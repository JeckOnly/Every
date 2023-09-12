package com.sjy.httpsdk.api

import com.sjy.httpsdk.dto.CatFact
import retrofit2.http.GET

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */
interface CatFactApi {

    @GET("/fact")
    suspend fun getOneFact(): CatFact
}