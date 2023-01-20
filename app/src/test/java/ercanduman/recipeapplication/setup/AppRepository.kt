package ercanduman.recipeapplication.setup

import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun observeCount(): Flow<Int>
}
