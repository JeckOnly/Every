package com.sjy.httpsdk.adapter

import com.sjy.httpsdk.exception.mapToException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber


/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

internal class CallWithExceptionHandling(
    private val delegate: Call<Any>
) : Call<Any> by delegate {

    override fun enqueue(callback: Callback<Any>) {
        delegate.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Timber.d("CustomAdapter onResponse")
                if (response.isSuccessful) {
                    Timber.d("response.isSuccessful")
                    callback.onResponse(call, response)
                } else {
                    Timber.d("response.isFailure")
                    callback.onFailure(call, mapToException(HttpException(response)))
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Timber.d("CustomAdapter onFailure")
                callback.onFailure(call, mapToException(t))
            }
        })
    }

    override fun clone() = CallWithExceptionHandling(delegate.clone())
}