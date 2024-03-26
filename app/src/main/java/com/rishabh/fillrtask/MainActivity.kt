package com.rishabh.fillrtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rishabh.fillrtask.presentation.image_detail.ImageDetailScreen
import com.rishabh.fillrtask.presentation.image_list.ImageListScreen
import com.rishabh.fillrtask.presentation.ui.Screen
import com.rishabh.fillrtask.presentation.ui.theme.FillrTaskTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity serves as the entry point for this app, setting up the navigation and theme.
 *
 * This activity sets the content view to use Jetpack Compose for the UI, applying the
 * FillrTaskTheme. It initializes a NavController for managing app navigation and defines the
 * navigation graph, specifying routes for the image list screen and image detail screen. The
 * MainActivity is annotated with @AndroidEntryPoint to enable dependency injection via Dagger-Hilt,
 * ensuring that ViewModel and other dependencies are provided to the composable functions.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FillrTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ImageListScreen.route
                    ) {
                        composable(
                            route = Screen.ImageListScreen.route
                        ) {
                            ImageListScreen(navController)
                        }
                        composable(
                            route = Screen.ImageDetailScreen.route + "/{index}",
                            arguments = listOf(navArgument("index") {
                                type = NavType.IntType
                            })
                        ) {
                            ImageDetailScreen()
                        }
                    }
                }
            }
        }
    }
}