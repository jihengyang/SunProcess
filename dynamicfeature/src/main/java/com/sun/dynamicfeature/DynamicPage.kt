package com.sun.dynamicfeature

import android.app.Activity
import android.os.Bundle

/**
 * @author hengyangji
 * on 2022/9/18
 */
class DynamicPage : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)
    }
}