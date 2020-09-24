package com.yakun.mallsdk.common

import android.app.Application

object MallContext {

    const val TAG = "MallContext"

    private lateinit var sContext: Application
    private var sDebug: Boolean = true

    fun getContext(): Application {
        return sContext
    }

    fun isDebug(): Boolean {
        return sDebug
    }

    fun init() {
        // TODO
    }
}