package com.brianml31.insta_moon

import android.app.Activity
import android.content.Intent
import com.brianml31.insta_moon.utils.FileUtils
import com.brianml31.insta_moon.utils.PermissionsUtils
import com.instagram.mainactivity.InstagramMainActivity

class InstagramMainActivity {
    companion object {
        private const val REQUEST_CODE_RESTORE_FILE = 74565

        fun after_onActivityResult(activity: Activity, requestCode: Int, resultCode: Int, data: Intent?) {
            if (data != null) {
                if (requestCode == REQUEST_CODE_RESTORE_FILE && data.data != null && resultCode == -1) {
                    FileUtils.importDevSettings(activity, data.data)
                }
            }
        }

        fun requestFileToRestore(instagramMainActivity: InstagramMainActivity) {
            if (!PermissionsUtils.checkPermission(instagramMainActivity)) {
                PermissionsUtils.requestPermission(instagramMainActivity)
            } else {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.addCategory("android.intent.category.OPENABLE")
                intent.setType("*/*")
                instagramMainActivity.startActivityForResult(intent, REQUEST_CODE_RESTORE_FILE)
            }
        }
    }

}