package com.sjy.httpsdk.exception

import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */

fun mapToException(throwable: Throwable): Exception {
    val result =  when (throwable) {
        is HttpException -> {
            when (val code = throwable.code()) {
                in 500..599 -> {
                    ApiException.ClientException(code)
                }

                in 400..499 -> {
                    ApiException.ServerException(code)
                }

                else -> {
                    ApiException.UnknownApiException(code)
                }
            }
        }

        is IOException -> {
            NoInternetException(throwable)
        }

        else -> {
            UnexpectedException(throwable)
        }
    }
    Timber.d("mapToException: $result")
    return result
}