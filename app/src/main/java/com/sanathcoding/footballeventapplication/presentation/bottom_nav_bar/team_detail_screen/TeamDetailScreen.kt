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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component.NestedScrollingView

@Composable
fun TeamDetailScreen(
    navController: NavController,
    teamDetailViewModel: TeamDetailViewModel = hiltViewModel()
) {
    val state = teamDetailViewModel.state
    val teamName = teamDetailViewModel.teamName
    val previousList = state.match?.matches?.previous
    val upComingList = state.match?.matches?.upcoming

    val scaffoldState = rememberScaffoldState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = teamName) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            "backIcon"
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White,
                elevation = 10.dp
            )
        },
        scaffoldState = scaffoldState
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .padding(paddingValue)
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
}