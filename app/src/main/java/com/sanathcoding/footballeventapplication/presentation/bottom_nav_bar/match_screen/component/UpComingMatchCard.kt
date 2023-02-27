package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.footballeventapplication.core.util.dateConverter
import com.sanathcoding.footballeventapplication.core.util.timeConverter
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming

@Composable
fun UpComingMatchCard(
    upcoming: Upcoming,
    modifier: Modifier = Modifier
) {

    val formattedTime = remember {
        timeConverter(upcoming.date)
    }

    val formattedDate = remember {
        dateConverter(upcoming.date)
    }

    Card(
        modifier = modifier
            .padding(16.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp),
            ),
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = upcoming.home,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = formattedTime,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Red
                    ),
                )
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    ),
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = upcoming.away,
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}