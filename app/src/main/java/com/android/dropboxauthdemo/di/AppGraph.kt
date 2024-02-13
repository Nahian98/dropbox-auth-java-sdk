package com.android.dropboxauthdemo.di

import com.android.dropboxauthdemo.api.DropboxApiWrapper
import com.android.dropboxauthdemo.api.DropboxCredentialUtil
import com.android.dropboxauthdemo.api.DropboxOAuthUtil

interface AppGraph {
    val dropboxCredentialUtil: DropboxCredentialUtil
    val dropboxOAuthUtil: DropboxOAuthUtil
    val dropboxApiWrapper: DropboxApiWrapper
}