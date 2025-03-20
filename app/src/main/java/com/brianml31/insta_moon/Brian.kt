package com.brianml31.insta_moon

import android.app.Application
import android.content.Context
import android.util.Base64
import com.brianml31.insta_moon.utils.PrefsUtils
import java.io.IOException
import java.net.URI

class Brian {
    companion object {
        private var ctx: Context? = null

        fun getCtx(): Context? {
            return ctx
        }

        fun setCtx(application: Application) {
            ctx = application.applicationContext
        }

        fun decodeBase64(encodedString: String): String {
            return String(Base64.decode(encodedString, Base64.DEFAULT))
        }

        fun hideSeenDM(): Boolean {
            return PrefsUtils.getBoolean(PrefsUtils.getKeysPreferences(1), false)
        }

        fun disableAds(): Boolean {
            return PrefsUtils.getBoolean(PrefsUtils.getKeysPreferences(3), false)
        }

        fun validateUriHost(uri: URI) {
            if(uri!=null){
                var uriPath: String = uri.path
                if(uriPath.contains("/v2/media/seen/")){
                    if (PrefsUtils.getBoolean(PrefsUtils.getKeysPreferences(0), false)) {
                        throw IOException("URL has no host")
                    }
                }
                if (uriPath.contains("/heartbeat_and_get_viewer_count/")) {
                    if (PrefsUtils.getBoolean(PrefsUtils.getKeysPreferences(2), false)) {
                        throw IOException("URL has no host")
                    }
                }
                if (uriPath.contains("graph.instagram.com") || uriPath.contains("graph.facebook.com") || uriPath.contains("/logging_client_events")) {
                    if (PrefsUtils.getBoolean(PrefsUtils.getKeysPreferences(4), false)) {
                        throw IOException("URL has no host")
                    }
                }
            }
        }

    }
}