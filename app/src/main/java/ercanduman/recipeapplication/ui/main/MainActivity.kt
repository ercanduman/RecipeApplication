package ercanduman.recipeapplication.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ercanduman.recipeapplication.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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
     * Details:
     *  1- https://developer.android.com/jetpack/compose/navigation#interoperability
     *  2- https://developer.android.com/guide/navigation/navigation-getting-started
     */
    private fun setupAppNavigation() {
    }
}
