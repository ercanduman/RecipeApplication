package ercanduman.recipeapplication

import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private fun observeCount() = flow<Int> {
        emit(1)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
