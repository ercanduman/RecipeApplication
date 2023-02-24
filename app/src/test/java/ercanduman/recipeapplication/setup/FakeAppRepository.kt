package ercanduman.recipeapplication.setup

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class FakeAppRepository(
    private val dataSource: FakeDataSource
) : AppRepository {
    override fun observeCount(): Flow<Int> {
        return dataSource.counts()
    }
}

class FakeDataSource {
    private val mutableFlow: MutableSharedFlow<Int> = MutableSharedFlow()

    suspend fun emitNewValue(value: Int) {
        mutableFlow.emit(value)
    }

    fun counts(): Flow<Int> {
        return mutableFlow.asSharedFlow()
    }
}
