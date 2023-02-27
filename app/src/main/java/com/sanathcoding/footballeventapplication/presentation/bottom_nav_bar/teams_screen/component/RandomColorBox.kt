package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.teams_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.domain.model.teams.Team

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RandomColorBox(
    team: Team,
    onTeamClick: (Team) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.background)
            .clickable { onTeamClick(team) },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            GlideImage(
                model = team.logo,
                contentDescription = stringResource(R.string.team_logo),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(Alignment.Start)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = team.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}