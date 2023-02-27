package com.sanathcoding.footballeventapplication.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable

@Composable
fun BottomNavBar(
    onSelectedItem: (Int) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(0) },
            icon = { Icon(imageVector = Icons.Filled.Add, contentDescription = "Map Icon") },
            enabled = true,
        )
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(1) },
            icon = { Icon(imageVector = Icons.Filled.List, contentDescription = "List Icon") },
            enabled = true,
        )
    }
}