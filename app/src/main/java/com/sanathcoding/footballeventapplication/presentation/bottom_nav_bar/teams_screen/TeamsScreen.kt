@file:OptIn(ExperimentalFoundationApi::class)

package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.CircularProgressIndicator
import androidx.wear.compose.material.Text
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.component.RandomColorBox

@Composable
fun TeamsScreen(
    navController: NavController,
    teamsViewModel: TeamsViewModel = hiltViewModel()
) {

    val state = teamsViewModel.state

    Box(
        modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .shadow(
            elevation = 10.dp,
            ambientColor = Color.Gray,
            spotColor = Color.Black,
            shape = RoundedCornerShape(10.dp),
        ),
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.team) { team ->
                RandomColorBox(team = team)
            }
        }
        if (state.error.isNotBlank())
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(
                        Alignment.Center
                    )
            )
        if (state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }

}