package com.r.snell.flightsearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Brightness7
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.r.snell.flightsearch.di.appModule
import com.r.snell.flightsearch.ui.nav.AppNavGraph
import com.r.snell.flightsearch.ui.nav.Destinations
import com.r.snell.flightsearch.ui.theme.FlightSearchTheme
import com.r.snell.flightsearch.viewmodel.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            val themeViewModel: ThemeViewModel = viewModel()
            val isDarkTheme by themeViewModel.isDarkTheme.collectAsState()

            FlightSearchTheme(darkTheme = isDarkTheme) {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopBarWithThemeToggle(
                            isDark = isDarkTheme,
                            onToggle = themeViewModel::toggleTheme
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        AppNavGraph(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val currentRoute = navController
        .currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == Destinations.FLIGHT_SEARCH,
            onClick = { navController.navigate(Destinations.FLIGHT_SEARCH) },
            label = { Text(stringResource(R.string.search)) },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") }
        )
        NavigationBarItem(
            selected = currentRoute == Destinations.FAVORITES,
            onClick = { navController.navigate(Destinations.FAVORITES) },
            label = { Text(stringResource(R.string.favorites)) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithThemeToggle(
    isDark: Boolean,
    onToggle: () -> Unit
) {
    TopAppBar(
        title = { Text("Flight Search App") },
        actions = {
            IconButton(onClick = onToggle) {
                Icon(
                    imageVector = if (isDark) Icons.Default.Brightness7 else Icons.Default.Brightness4,
                    contentDescription = stringResource(R.string.toggle_theme)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FlightSearchTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                TopBarWithThemeToggle(
                    isDark = false,
                    onToggle = {}
                )
            },
            bottomBar = {
                BottomNavigationBar(rememberNavController())
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                Text("Content Preview Here", modifier = Modifier.padding(16.dp))
            }
        }
    }
}