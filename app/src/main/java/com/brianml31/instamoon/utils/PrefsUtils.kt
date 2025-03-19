package com.brianml31.insta_moon.utils

import android.content.Context
import android.content.SharedPreferences
import com.brianml31.insta_moon.Brian

class PrefsUtils {
    companion object{
        fun getBoolean(key: String?, defValue: Boolean): Boolean {
            return getSharedPreferences(Brian.getCtx()!!).getBoolean(key, defValue)
        }

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(context.packageName + "_brian", 0)
        }

        fun savePreferences(ctx: Context, checkedItems: BooleanArray) {
            val preferences = getSharedPreferences(ctx)
            val editor = preferences.edit()
            for (i in checkedItems.indices) {
                editor.putBoolean(getKeysPreferences(i), checkedItems[i])
            }
            editor.apply()
        }

        fun loadPreferences(ctx: Context): BooleanArray {
            val preferences = getSharedPreferences(ctx)
            val checkedItems = booleanArrayOf(false, false, false, false)
            for (i in checkedItems.indices) {
                checkedItems[i] = preferences.getBoolean(getKeysPreferences(i), false)
            }
            return checkedItems
        }

        fun getKeysPreferences(position: Int): String {
            val keys = arrayOf("hide_seen_stories", "hide_seen_messages","hide_seen_live_videos", "disable_analytics")
            return keys[position]
        }
    }
}