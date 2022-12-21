package ercanduman.recipeapplication.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import ercanduman.recipeapplication.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAppNavigation()
    }

    /**
     * The recommendation for mixed Compose and Views apps is to use the Fragment-based Navigation
     * component. Fragments will then hold View-based screens, Compose screens, and screens that use
     * both Views and Compose. Once each Fragment's contents are in Compose, the next step is to tie
     * all of those screens together with Navigation Compose and remove all of the Fragments.
     *
     * Details: https://developer.android.com/jetpack/compose/navigation#interoperability
     */
    private fun setupAppNavigation() {
    }
}
