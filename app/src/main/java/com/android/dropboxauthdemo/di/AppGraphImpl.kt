package com.android.dropboxauthdemo.di

import android.content.Context
import com.android.dropboxauthdemo.api.DropboxApiWrapper
import com.android.dropboxauthdemo.api.DropboxAppConfig
import com.android.dropboxauthdemo.api.DropboxCredentialUtil
import com.android.dropboxauthdemo.api.DropboxOAuthUtil

internal class AppGraphImpl(context: Context) : AppGraph {
    private val dropboxAppConfig = DropboxAppConfig()

    override val dropboxCredentialUtil by lazy { DropboxCredentialUtil(context.applicationContext) }

    override val dropboxOAuthUtil by lazy {
        DropboxOAuthUtil(
            dropboxAppConfig = dropboxAppConfig,
            dropboxCredentialUtil = dropboxCredentialUtil
        )
    }

    override val dropboxApiWrapper
        get() = DropboxApiWrapper(
            dbxCredential = dropboxCredentialUtil.readCredentialLocally()!!,
            clientIdentifier = dropboxAppConfig.clientIdentifier
        )
}