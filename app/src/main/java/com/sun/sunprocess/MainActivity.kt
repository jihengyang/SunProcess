package com.sun.sunprocess

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.Resources
import android.os.Bundle
import android.os.IBinder
import android.os.Process
import android.util.Log
import android.view.View
import android.widget.Toast
import com.sun.sunipcserver.ISunLike
import com.sun.testprocess.AppStatus

class MainActivity : Activity() {
    private var sunServer: ISunLike? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.text).setOnClickListener {
            bindService()
        }
    }

    override fun getResources(): Resources {
        return super.getResources()
    }

    private fun bindService() {
        val intent = Intent("com.sun.sunipcserver.service")
        intent.setPackage("com.sun.sunipcserver")
//        val startRes = startForegroundService(intent)
        val success = bindService(intent, object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                sunServer = ISunLike.Stub.asInterface(service)
                Log.i("MyBinder", "receive hash:${sunServer.hashCode()}")
                Toast.makeText(this@MainActivity, sunServer?.name, Toast.LENGTH_SHORT).show()
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.i("MainActivity", "onServiceDisconnected")
            }

            override fun onBindingDied(name: ComponentName?) {
                super.onBindingDied(name)
            }

            override fun onNullBinding(name: ComponentName?) {
                super.onNullBinding(name)
            }
        }, BIND_AUTO_CREATE)

        Log.i("MainActivity", "bindService")
    }
}