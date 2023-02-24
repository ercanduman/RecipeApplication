@file:Suppress("OPT_IN_USAGE", "HardCodedStringLiteral")

package ercanduman.recipeapplication.setup

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SharedFlowTest {

    @Test
    fun `run tickHandler and print emitted values`() {
        runTest {
            println("Test started.")

            val collectJob: Job = launch {
                println("collectJob is started.")
                val tickHandler = TickHandler(this)
                val emittedValue: SharedFlow<Int> = tickHandler.tickFlow
                emittedValue.collect {
                    println("collectJob collected: $it")
                }
                println("collectJob is finished.")
            }

            println("Test delay started.")
            delay(30L)
            println("Test delay finished.")
            collectJob.cancel()
            println("collectJob cancelled.")
            println("Test finished.")
        }
    }
}

class TickHandler(
    externalScope: CoroutineScope
) {
    private val _tickFlow: MutableSharedFlow<Int> = MutableSharedFlow(0)
    val tickFlow: SharedFlow<Int> = _tickFlow.asSharedFlow()

    private var counter: Int = 0
    private val tickIntervalMs: Long = 1

    init {
        println("init called.")
        externalScope.launch {
            println("Coroutine scope stared.")
            while (true) {
                println("Before delay")
                delay(tickIntervalMs)
                println("After delay")
                println("Emitted")
                _tickFlow.emit(counter++)
            }
            println("Coroutine scope finished.")
        }
        println("init finished.")
    }
}
