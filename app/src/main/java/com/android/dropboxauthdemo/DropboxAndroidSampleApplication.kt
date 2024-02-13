package com.android.dropboxauthdemo

import android.app.Application
import com.android.dropboxauthdemo.di.AppGraph
import com.android.dropboxauthdemo.di.AppGraphImpl

class DropboxAndroidSampleApplication : Application() {
    val appGraph: AppGraph = AppGraphImpl(this)
}