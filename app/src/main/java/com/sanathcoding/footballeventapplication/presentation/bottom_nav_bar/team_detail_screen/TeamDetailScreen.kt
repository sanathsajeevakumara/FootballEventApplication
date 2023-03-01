package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.team_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.CircularProgressIndicator
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component.NestedScrollingView

@Composable
fun TeamDetailScreen(
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel()
) {
    val state = teamDetailViewModel.state
    val previousList = state.match?.matches?.previous
    val upComingList = state.match?.matches?.upcoming
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        // Nested Scrolling view
        NestedScrollingView(previousList, upComingList)

        // Check the state is in error state
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

        // Check the state is in loading state
        if (state.isLoading)
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }

}