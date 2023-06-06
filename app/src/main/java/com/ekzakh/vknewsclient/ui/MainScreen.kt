package com.ekzakh.vknewsclient.ui

import android.util.Log
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Log.d("MainScreen", snackbarHostState.currentSnackbarData.toString())
    val fabIsVisible = remember { mutableStateOf(true) }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            if (fabIsVisible.value) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        val action = snackbarHostState.showSnackbar(
                            message = "Snack bar",
                            actionLabel = "Hide FAB",
                            duration = SnackbarDuration.Long,
                        )
                        if (action == SnackbarResult.ActionPerformed) {
                            fabIsVisible.value = false
                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                    )
                }
            }
        },
        bottomBar = {
            BottomAppBar() {
                val items =
                    listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)
                val selectedIndex = remember {
                    mutableStateOf(0)
                }
                items.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = index == selectedIndex.value,
                        onClick = { selectedIndex.value = index },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = stringResource(
                                    id = navigationItem.titleResId,
                                ),
                            )
                        },
                        label = {
                            Text(stringResource(id = navigationItem.titleResId))
                        },
                    )
                }
            }
        },
    ) {}
}
