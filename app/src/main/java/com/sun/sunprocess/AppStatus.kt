package com.sun.testprocess

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.reflect.Method


/**
 * @author hengyangji
 * on 2022/8/1
 */
object AppStatus {
    var processName = ""

    @RequiresApi(Build.VERSION_CODES.P)
    fun init() {
        processName = Application.getProcessName()
    }

    fun isInitProcess() = processName.contains("init")

    fun clearRecent(context: Context) {
        try {
            val tasksManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val method: Method = tasksManager.javaClass.getMethod("getService")
            method.isAccessible = true
            val getService: Any = method.invoke(tasksManager) as Any
            val removeTaskMethod = getService.javaClass.getMethod("removeTask", Int::class.java)
            removeTaskMethod.isAccessible = true
            val recentTasks = tasksManager.getRecentTasks(Int.MAX_VALUE, PackageManager.MATCH_ALL)
            for (recentTask in recentTasks) {
                if (recentTask != null) {
                    val taskId =
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) recentTask.taskId else recentTask.persistentId
                    removeTaskMethod.invoke(getService, taskId)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}