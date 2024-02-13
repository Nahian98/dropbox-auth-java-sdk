# Dropbox-authentication-java-sdk


Dropbox authentication implemented for android java sdk. File traversal and file download is also implemented.

## Installation

Install latest android studio.

## Create an app from Dropbox Developer's console

Click [here](https://www.dropbox.com/developers/documentation/) and go to app console

## Then Give specific scopes in the app console permissions section
For this project the scopes are 

```kotlin
val scopes = listOf(
            "account_info.read",
            "files.content.write",
            "files.content.read",
            "sharing.read"
        )
```

## Add this to the AndroidManifest.xml
Replace YOUR_APP_KEY with the app key you got from app console
```kotlin
<activity
            android:name="com.dropbox.core.android.AuthActivity"
            android:configChanges="orientation|keyboard"
            android:exported="true"
            android:launchMode="singleTask">
            <intent-filter>
                <data android:scheme="db-YOUR_APP_KEY" />
            
                <action android:name="android.intent.action.VIEW" />
            
                <category android:name="android.intent.category.BROWSABLE" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
            
            <!-- Additional intent-filter required as a workaround for Apps using targetSdk=33 until the fix in the Dropbox app is available to all users. -->
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />
            
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
</activity>
```
## For dropbox auth without refresh token

```kotlin
private fun startDropboxAuthorizationOAuth2(context: Context) {
        Auth.startOAuth2Authentication(context, dropboxAppConfig.apiKey)
        isAwaitingResult = true
}
```

## For dropbox auth with refresh token

```kotlin
private fun startDropboxAuthorization2PKCE() {
        val requestConfig = DbxRequestConfig(dropboxAppConfig.clientIdentifier)

        // The scope's your app will need from Dropbox
        // Read more about Scopes here: https://developers.dropbox.com/oauth-guide#dropbox-api-permissions
        val scopes = listOf(
            "account_info.read",
            "files.content.write",
            "files.content.read",
            "sharing.read"
        )
        Auth.startOAuth2PKCE(this, dropboxAppConfig.apiKey, requestConfig, scopes)
        isAwaitingResult = true
}
```

## Handling refresh mechanism

```kotlin
fun makeDropboxClient() {
        dbxClientV2 = DbxClientV2(
            DbxRequestConfig(DropboxAppConfig().clientIdentifier),
            readCredentialLocally()
        )

        // Refresh mechanism
        CoroutineScope(Dispatchers.IO).launch {
            try {
                dbxClientV2.refreshAccessToken()
                Log.d("_Refresh", "Refreshed successfully")
            } catch (e: Exception) {
                Log.d("_RefreshExceptionDrop", "$e")
            }
        }
}
```
