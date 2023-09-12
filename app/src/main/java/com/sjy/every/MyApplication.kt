package com.sjy.every

import android.app.Application
import com.sjy.util.FileClassMethodTag
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by JeckOnly on 2023/9/7
 * Describe:
 */
@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // Timber注册
        if (BuildConfig.DEBUG) {
            Timber.plant(FileClassMethodTag())
        }
    }
}