package com.sjy.httpsdk.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */


class ExceptionCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, Call<*>>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType || returnType.actualTypeArguments.size != 1) {
            return null
        }

        // retrofit中adapter的列表，我们自定义的在前面，默认的添加在后面
        val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
        @Suppress("UNCHECKED_CAST")
        return ExceptionCallAdapter(
            delegateAdapter = delegate as CallAdapter<Any, Call<*>>
        )
    }
}