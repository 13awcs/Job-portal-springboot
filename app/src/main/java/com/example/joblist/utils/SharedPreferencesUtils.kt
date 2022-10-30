package com.example.joblist.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

object SharedPreferencesUtils {
    fun saveString(context: Context, map: Map<String, String> ) {
        context.getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE).edit().apply {
            map.entries.forEach {
                putString(it.key, it.value)
            }
            apply()
        }
    }

    fun loadString(context: Context, key: String): String {
        return context.getSharedPreferences(Constants.SHARED_PREFS, MODE_PRIVATE).getString(key, "")!!
    }
}
