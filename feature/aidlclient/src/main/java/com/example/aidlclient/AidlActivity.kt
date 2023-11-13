package com.example.aidlclient

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.sjy.aidlclient.R
import com.sjy.aidlserver.CleanCallback
import com.sjy.aidlserver.CleanOTAInterface
import timber.log.Timber

class AidlActivity : AppCompatActivity() {

    private lateinit var cleanOTAInterface: CleanOTAInterface

    private val cleanCacheConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Timber.d("onServiceConnected")
            cleanOTAInterface = CleanOTAInterface.Stub.asInterface(service)


        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // do something
            Timber.d("onServiceDisconnected")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent()

        intent.component = ComponentName(
            "com.sjy.aidlserver",
            "com.example.aidlserver.CleanOTAService"
        )

        bindService(intent, cleanCacheConnection, BIND_AUTO_CREATE)

        findViewById<Button>(R.id.button).setOnClickListener {
            cleanOTAInterface.cleanOTACache(object : CleanCallback.Stub() {
                override fun onCleanResult(result: Boolean) {
                    Timber.d("onCleanResult: $result")
                }
            })
        }
    }
}