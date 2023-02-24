@file:OptIn(ExperimentalCoroutinesApi::class)

package ercanduman.recipeapplication.test

import com.google.common.truth.Truth.assertThat
import ercanduman.recipeapplication.setup.AppRepository
import ercanduman.recipeapplication.setup.FakeAppRepository
import ercanduman.recipeapplication.setup.FakeDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AppRepositoryTest {

    @Test
    fun test_observed_flow_list_empty(): TestResult = runTest {
        val dataSource = FakeDataSource()
        val repository: AppRepository = FakeAppRepository(dataSource)

        val observedFlowList = mutableListOf<Int>()
        val flowCollectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.observeCount().toList(observedFlowList)
        }

        assertThat(observedFlowList).isEmpty()

        flowCollectorJob.cancel()
    }

    @Test
    fun test_observed_flow_list_has_emitted_first_value(): TestResult = runTest {
        val dataSource = FakeDataSource()
        val repository: AppRepository = FakeAppRepository(dataSource)

        val observedFlowList = mutableListOf<Int>()
        val flowCollectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.observeCount().toList(observedFlowList)
        }

        val newValue = 111
        dataSource.emitNewValue(newValue)

        assertThat(observedFlowList.first()).isEqualTo(newValue)

        flowCollectorJob.cancel()
    }

    @Test
    fun test_observed_flow_list_has_correct_emitted_values(): TestResult = runTest {
        val dataSource = FakeDataSource()
        val repository: AppRepository = FakeAppRepository(dataSource)

        val observedCountList = mutableListOf<Int>()
        val flowCollectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.observeCount().toList(observedCountList)
        }

        assertThat(observedCountList).isEmpty()

        val newValueList = listOf(60, 70, 80, 90)
        newValueList.forEach { dataSource.emitNewValue(it) }
        assertThat(observedCountList.last()).isEqualTo(newValueList.last())

        flowCollectorJob.cancel()
    }

    @Test
    fun test_observed_flow_list_has_specific_emitted_values(): TestResult = runTest {
        val dataSource = FakeDataSource()
        val repository: AppRepository = FakeAppRepository(dataSource)

        val observedCountList = mutableListOf<Int>()
        val flowCollectorJob = launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.observeCount().toList(observedCountList)
        }

        assertThat(observedCountList).isEmpty()

        val newValueList = listOf(60, 70, 80, 90)
        newValueList.forEach { dataSource.emitNewValue(it) }
        assertThat(newValueList.last()).isEqualTo(observedCountList.last())

        val additionalValue = 100
        dataSource.emitNewValue(additionalValue)
        assertThat(additionalValue).isEqualTo(observedCountList.last())

        flowCollectorJob.cancel()
    }
}
