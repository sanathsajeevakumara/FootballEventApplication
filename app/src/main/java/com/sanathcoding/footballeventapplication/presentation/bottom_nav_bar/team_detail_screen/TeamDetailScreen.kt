package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.team_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.CircularProgressIndicator
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component.PreviousMatchCard
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component.UpComingMatchCard
import com.sanathcoding.footballeventapplication.presentation.navigation.Screen

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.previous_match),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            previousList?.let {
                LazyRow(content = {
                    items(it) { previous ->
                        PreviousMatchCard(
                            previous = previous,
                            modifier = Modifier
                                .height(250.dp)
                        )
                    }
                })
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.upcooming_match),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
            upComingList?.let {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(it) { upComing ->
                        UpComingMatchCard(upcoming = upComing)
                    }
                }
            }
        }
        if (state.error.isNotBlank())
            androidx.wear.compose.material.Text(
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