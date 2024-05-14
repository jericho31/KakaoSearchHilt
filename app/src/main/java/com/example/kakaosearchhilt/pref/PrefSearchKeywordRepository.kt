package com.example.kakaosearchhilt.pref

import android.content.Context

private const val prefName = "SearchKeyword"
private const val key = "key"

interface PrefSearchKeywordRepository {
    fun save(value: String)
    fun load(): String?
}

class PrefStringRepositoryImpl(context: Context) : PrefSearchKeywordRepository {
    private val pref = context.getSharedPreferences(prefName, 0)

    override fun save(value: String) = pref.edit().putString(key, value).apply()
    override fun load() = pref.getString(key, null)
}
