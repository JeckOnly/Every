package com.sjy.catfact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sjy.httpsdk.api.CatFactApi
import com.sjy.httpsdk.api.CatFactSandwichApi
import com.sjy.httpsdk.dto.CatFact
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.onSuccess
import com.skydoves.sandwich.retry.RetryPolicy
import com.skydoves.sandwich.retry.runAndRetry
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by JeckOnly on 2023/9/12
 * Describe:
 */
@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val catFactApi: CatFactApi,
    private val catFactSandwichApi: CatFactSandwichApi
) : ViewModel() {

    private val catFactFlow: MutableStateFlow<CatFact?> = MutableStateFlow(null)
    val catFact: StateFlow<CatFact?> = catFactFlow

    /**
     * 使用自定义CallAdapter的方式
     */
    fun askCatFact() {
        viewModelScope.launch {
            try {
                val catFactResult = catFactApi.getOneFact()
                Timber.d("catFactResult: $catFactResult")
                catFactFlow.update {
                    catFactResult
                }
            } catch (e: Exception) {
                Timber.d("catFactResult: ${e}, cause: ${e.cause}")
            }
        }
    }

    /**
     * 使用Sandwich这个库 when
     */
    fun askCatFactSandwich() {
        viewModelScope.launch {
            val catFactResult = catFactSandwichApi.getOneFact()
            Timber.d("catFactResultSandwich: $catFactResult")
            when (catFactResult) {
                is ApiResponse.Failure.Error -> {
                    Timber.d("catFactResultSandwich: ${catFactResult.statusCode}")
                }

                is ApiResponse.Failure.Exception -> {
                    Timber.d("catFactResultSandwich: ${catFactResult.message}, cause: ${catFactResult.exception}")
                }

                is ApiResponse.Success -> {
                    Timber.d("catFactResultSandwich: ${catFactResult.data}")
                    catFactFlow.update {
                        catFactResult.data
                    }
                }
            }
        }
    }

    /**
     * 使用Sandwich这个库 onXXX
     */
    fun askCatFactSandwich2() {
        viewModelScope.launch {
            val catFactResult = catFactSandwichApi.getOneFact()
            Timber.d("catFactResultSandwich: $catFactResult")

            catFactResult.suspendOnSuccess {
                Timber.d("catFactResultSandwich: ${this.data}")
                catFactFlow.update {
                    this.data
                }
            }.suspendOnException {
                Timber.d("catFactResultSandwich: ${this.message}, cause: ${this.exception}")
            }.suspendOnError {
                Timber.d("catFactResultSandwich: ${this.statusCode}")
            }

        }
    }

    /**
     * 使用Sandwich这个库 add retry
     */
    fun askCatFactSandwich3() {
        viewModelScope.launch {
            runAndRetry(object : RetryPolicy {
                override fun retryTimeout(attempt: Int, message: String?): Int {
                    return 2000
                }

                override fun shouldRetry(attempt: Int, message: String?): Boolean {
                    return attempt < 3
                }

            }) { attempt: Int, reason: String? ->
                Timber.d("attempt: $attempt, reason: $reason")
                catFactSandwichApi.getOneFact().suspendOnSuccess {
                    Timber.d("catFactResultSandwich: ${this.data}")
                    catFactFlow.update {
                        this.data
                    }
                }.suspendOnException {
                    Timber.d("catFactResultSandwich: ${this.message}, cause: ${this.exception}")
                }.suspendOnError {
                    Timber.d("catFactResultSandwich: ${this.statusCode}")
                }
            }
        }
    }
}