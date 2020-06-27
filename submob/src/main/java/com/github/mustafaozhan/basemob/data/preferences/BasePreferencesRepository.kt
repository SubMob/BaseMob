/*
 Copyright (c) 2020 Mustafa Ozhan. All rights reserved.
 */
package com.github.mustafaozhan.basemob.data.preferences

import android.content.Context
import androidx.core.content.edit
import com.github.mustafaozhan.basemob.error.SharedPreferencesException

abstract class BasePreferencesRepository(val context: Context) {

    protected abstract val preferencesName: String

    private val preferences
        get() = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    @Suppress("UNCHECKED_CAST")
    fun <T> getValue(key: String, defaultValue: T): T = when (defaultValue) {
        is Long -> preferences.getLong(key, defaultValue) as T
        is String -> preferences.getString(key, defaultValue) as T
        is Int -> preferences.getInt(key, defaultValue) as T
        is Boolean -> preferences.getBoolean(key, defaultValue) as T
        is Float -> preferences.getFloat(key, defaultValue) as T
        is Set<*> -> preferences.getStringSet(key, defaultValue as Set<String>) as T
        is MutableSet<*> -> preferences.getStringSet(key, defaultValue as MutableSet<String>) as T
        else -> defaultValue
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> setValue(key: String, value: T) = preferences.edit {
        when (value) {
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Set<*> -> putStringSet(key, value as Set<String>)
            is MutableSet<*> -> putStringSet(key, value as MutableSet<String>)
            else -> throw SharedPreferencesException()
        }
    }
}
