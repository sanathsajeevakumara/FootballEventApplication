package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.domain.model.teams.Team


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamListCard(
    team: Team,
    onTeamClick: (Team) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        backgroundColor = MaterialTheme.colors.onBackground,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(16.dp)
            .clickable { onTeamClick(team) }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            GlideImage(
                model = team.logo,
                contentDescription = stringResource(R.string.team_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = team.name,
                style = MaterialTheme.typography.h4,
                color = Color.Black
            )
        }
    }
}