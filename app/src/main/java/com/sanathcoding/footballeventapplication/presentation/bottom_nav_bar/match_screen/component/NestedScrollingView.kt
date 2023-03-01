package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.domain.model.match.Previous
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming

@Composable
fun NestedScrollingView(
    previousList: List<Previous>?,
    upComingList: List<Upcoming>?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colors.background)
    ) {
        item {
            Text(
                text = stringResource(R.string.previous_match),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))

            PreviousList(previousList)

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.upcooming_match),
                style = MaterialTheme.typography.h4
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        upComingList(upComingList)
    }
}