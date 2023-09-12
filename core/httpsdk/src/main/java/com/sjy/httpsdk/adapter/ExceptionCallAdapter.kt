package com.sjy.httpsdk.adapter

import retrofit2.Call
import retrofit2.CallAdapter

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

internal class ExceptionCallAdapter(
    private val delegateAdapter: CallAdapter<Any, Call<*>>
) : CallAdapter<Any, Call<*>> by delegateAdapter {

    override fun adapt(call: Call<Any>): Call<*> {
        return delegateAdapter.adapt(CallWithExceptionHandling(call))
    }
}