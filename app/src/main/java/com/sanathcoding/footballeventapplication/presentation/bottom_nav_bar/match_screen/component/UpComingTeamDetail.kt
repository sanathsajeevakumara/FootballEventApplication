package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming
import com.sanathcoding.footballeventapplication.ui.theme.Orange

@Composable
fun UpComingTeamDetail(
    upcoming: Upcoming,
    formattedTime: String,
    formattedDate: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = upcoming.home,
            style = MaterialTheme.typography.h6.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        )

        Column {
            Text(
                text = formattedTime,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Orange
                ),
            )
            Text(
                text = formattedDate,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                ),
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = upcoming.away,
            style = MaterialTheme.typography.h6.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}