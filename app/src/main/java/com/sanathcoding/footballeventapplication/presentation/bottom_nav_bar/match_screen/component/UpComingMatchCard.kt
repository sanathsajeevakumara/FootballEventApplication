package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.footballeventapplication.core.util.dateConverter
import com.sanathcoding.footballeventapplication.core.util.timeConverter
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming
import com.sanathcoding.footballeventapplication.presentation.util.requestNotificationPermission
import com.sanathcoding.footballeventapplication.ui.theme.Orange

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

    var showReminderPopUp by remember {
        mutableStateOf(false)
    }

    val hasNotificationPermission = requestNotificationPermission()

    Card(
        modifier = modifier
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .clickable {
                showReminderPopUp = true
            },
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(4.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
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
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }

            Row(
                modifier = Modifier.padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = upcoming.description,
                    style = MaterialTheme.typography.h6.copy(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    if (hasNotificationPermission) {
        if (showReminderPopUp) SetReminderPopUp(upcoming = upcoming) {
            showReminderPopUp = false
        }
    }


}