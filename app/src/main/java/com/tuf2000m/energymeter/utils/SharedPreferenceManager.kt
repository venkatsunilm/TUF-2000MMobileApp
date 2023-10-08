package com.tuf2000m.energymeter.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.tuf2000m.energymeter.data.model.auth.Auth
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferenceManager @Inject constructor(@ApplicationContext val context: Context) {

    private val PREFS_NAME = "userpref"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUSer(user: Auth) {
        sharedPref.edit().putString("user", Gson().toJson(user)).apply()
    }

    fun getUser(): Auth? {
        val data = sharedPref.getString("user", null) ?: return null
        return Gson().fromJson(data, Auth::class.java)
    }
    fun clearData(){
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }

}