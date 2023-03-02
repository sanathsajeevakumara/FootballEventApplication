package com.sanathcoding.footballeventapplication.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.IdlingResource
import com.sanathcoding.footballeventapplication.core.util.SimpleIdlingResource
import com.sanathcoding.footballeventapplication.presentation.navigation.SetUpNavGraph
import com.sanathcoding.footballeventapplication.ui.theme.FootballEventApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FootballEventApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    SetUpNavGraph(navController = navController)
                }
            }
        }
    }



    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */

    @Nullable
    private var mIdlingResource: SimpleIdlingResource? = null

    @VisibleForTesting
    @NonNull
    fun getIdlingResource(): IdlingResource {
        if (mIdlingResource == null) {
            mIdlingResource = SimpleIdlingResource()
        }
        return mIdlingResource as SimpleIdlingResource
    }

}