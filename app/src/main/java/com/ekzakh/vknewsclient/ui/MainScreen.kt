package com.ekzakh.vknewsclient.ui

import androidx.compose.foundation.background
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ekzakh.vknewsclient.navigation.AppNavGraph

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navHostController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colors.background),
        bottomBar = {
            BottomAppBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favorite,
                    NavigationItem.Profile,
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navHostController.navigate(item.screen.route) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = stringResource(
                                    id = item.titleResId,
                                ),
                            )
                        },
                        label = {
                            Text(stringResource(id = item.titleResId))
                        },
                    )
                }
            }
        },
    ) { padding ->
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(viewModel = viewModel, padding = padding)
            },
            favoriteScreenContent = { Text("TEST") },
            profileScreenContent = { Text("TEST") },
        )
    }
}
