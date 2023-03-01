@file:OptIn(ExperimentalFoundationApi::class)

package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sanathcoding.footballeventapplication.core.common.TestTag.PROGRESS_INDICATOR
import com.sanathcoding.footballeventapplication.core.common.TestTag.TEAM_LIST
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.component.RandomColorBox
import com.sanathcoding.footballeventapplication.presentation.navigation.Screen

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
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            modifier = Modifier.fillMaxSize().testTag(TEAM_LIST),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.team) { team ->
                RandomColorBox(
                    team = team,
                    onTeamClick = {
                        navController.navigate(Screen.TeamDetailScreen.route + "/${team.id}")
                    }
                )
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
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).testTag(PROGRESS_INDICATOR))
    }

}