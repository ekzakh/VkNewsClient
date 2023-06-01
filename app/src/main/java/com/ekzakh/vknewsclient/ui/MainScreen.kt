package com.ekzakh.vknewsclient.ui

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen() {
    Scaffold(
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
