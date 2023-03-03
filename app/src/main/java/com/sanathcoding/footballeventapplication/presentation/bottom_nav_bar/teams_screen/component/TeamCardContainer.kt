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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.common.TestTag.TEAM_ITEM
import com.sanathcoding.footballeventapplication.domain.model.teams.Team

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeamCardContainer(
    team: Team,
    onTeamClick: (Team) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable { onTeamClick(team) }
            .semantics {
                       contentDescription = TEAM_ITEM
            },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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