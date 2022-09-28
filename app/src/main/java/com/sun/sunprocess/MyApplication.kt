package com.sun.sunprocess

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.util.Log
import androidx.annotation.RequiresApi
import com.sun.testprocess.AppStatus

/**
 * @author hengyangji
 * on 2022/8/1
 */
class MyApplication : Application() {
    init {
        Log.i(TAG, "onInit")
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        AppStatus.init()
//        if (!AppStatus.isInitProcess()) {
//            startInitProcess(base)
//            AppStatus.clearRecent(base)
//        }
    }

    companion object {
        private const val TAG = "MyApplication"
    }
}

fun startInitProcess(context: Context?, extras: Bundle? = null) {
    if (context == null) {
        Log.w("InitAppUtils", "startInitProcess context == null, return")
        return
    }
    val intent = Intent(context, InitActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NO_ANIMATION)
    if (extras != null) {
        intent.putExtras(extras)
    }
    context.startActivity(intent)
}