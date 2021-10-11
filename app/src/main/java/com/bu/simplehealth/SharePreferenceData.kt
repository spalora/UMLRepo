package com.bu.simplehealth

import android.content.Context
import androidx.preference.PreferenceManager

/**
 * @author Sandeep Agrawal
 * This class is to handle the shared preferences in the application
 */
object SharePreferenceData {

    /**
     * This method set String value in shared preference.
     *
     * @param context - Current context
     * @param key     - String representation the key
     * @param value   -String representing the default value
     */
    fun setSharedPrefString(context: Context?, key: String, value: String) {
        val settings = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    /**
     * Returns String value as per preference key passed.
     *
     * @param context      - Current context
     * @param key          - String representation the key
     * @param defaultValue -String representing the default value
     * @return String
     */
    fun getSharedPrefString(context: Context?, key: String, defaultValue: String): String? {
        val token = PreferenceManager.getDefaultSharedPreferences(context)
        return token.getString(key, defaultValue)
    }

    fun clearAllPreference(context: Context?) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}
