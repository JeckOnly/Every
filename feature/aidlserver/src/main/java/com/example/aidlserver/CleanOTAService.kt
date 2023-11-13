package com.example.aidlserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import com.sjy.aidlserver.CleanCallback
import com.sjy.aidlserver.CleanOTAInterface


class CleanOTAService : Service() {

    private val cleanOTAImpl: CleanOTAInterface.Stub = object : CleanOTAInterface.Stub() {
        override fun cleanOTACache(callback: CleanCallback?) {
            // clean cache
            callback?.onCleanResult(true)
        }

    }

    override fun onBind(intent: Intent): IBinder {
        return cleanOTAImpl
    }
}
