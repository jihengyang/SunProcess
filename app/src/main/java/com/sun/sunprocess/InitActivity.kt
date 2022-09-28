package com.sun.sunprocess

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import com.sun.sunipcserver.ISunLike

/**
 * @author hengyangji
 * on 2022/8/1
 */
class InitActivity : Activity() {
    private var sunServer: ISunLike? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        val intent = Intent("com.sun.sunipcserver.service")
        intent.setPackage("com.sun.sunipcserver")
        bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                sunServer = ISunLike.Stub.asInterface(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
            }

        }, BIND_AUTO_CREATE)

        findViewById<View>(R.id.text).setOnClickListener {
            Toast.makeText(this, sunServer?.name, Toast.LENGTH_SHORT).show()
        }
    }
}