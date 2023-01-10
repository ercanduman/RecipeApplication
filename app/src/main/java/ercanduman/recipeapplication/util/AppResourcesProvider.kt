package ercanduman.recipeapplication.util

import android.content.Context

class AppResourcesProvider(
    private val appContext: Context
) {
    fun getString(resourceId: Int): String {
        return appContext.getString(resourceId)
    }

    fun getString(resourceId: Int, substitutingValue: String): String {
        return appContext.getString(resourceId, substitutingValue)
    }
}
