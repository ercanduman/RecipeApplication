@file:Suppress("OPT_IN_USAGE")

package ercanduman.recipeapplication.setup

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SharedFlowTest2 {

    @Test
    fun `run tickHandler and print emitted values`() {
        runTest {
            println("Before collection.")

            val tickHandler = TickHandler2()
            val emittedValue = tickHandler.tickFlow

            val collectJob: Job = launch {
                emittedValue.collect {
                    println("Collected: $it")
                }
            }
            println("After collection.")
            delay(10000L)
            collectJob.cancel()
        }
    }
}

class TickHandler2() {
    private var counter: Int = 0
    private val tickIntervalMs: Long = 1000

    val tickFlow: Flow<Int> = flow {
        println("Coroutine scope stared.")
        while (true) {
            println("Before delay")
            delay(tickIntervalMs)
            println("After delay")
            emit(counter++)
            println("After emit")
        }
        println("Coroutine scope finished.")
    }
}
