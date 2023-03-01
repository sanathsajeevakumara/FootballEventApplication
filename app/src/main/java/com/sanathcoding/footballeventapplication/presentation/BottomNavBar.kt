package com.sanathcoding.footballeventapplication.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.sanathcoding.footballeventapplication.R

@Composable
fun BottomNavBar(
    onSelectedItem: (Int) -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(0) },
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.team),
                    contentDescription = "Team Icon"
                ) },
            enabled = true,
            alwaysShowLabel = true,
            label = {
                Text(
                    text = "Teams",
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { onSelectedItem(1) },
            icon = { Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.tv),
                contentDescription = "List Icon"
            ) },
            enabled = true,
            alwaysShowLabel = true,
            label = {
                Text(
                    text = "Match Details",
                    modifier = Modifier.padding(16.dp)
                )
            }
        )
    }
}