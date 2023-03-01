@file:OptIn(ExperimentalMaterialApi::class)

package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.footballeventapplication.core.util.dateConverter
import com.sanathcoding.footballeventapplication.core.util.timeConverter
import com.sanathcoding.footballeventapplication.domain.model.match.Previous
import com.sanathcoding.footballeventapplication.ui.theme.Gold

@Composable
fun PreviousMatchCard(
    previous: Previous,
    modifier: Modifier = Modifier
) {
    val formattedTime = remember {
        timeConverter(previous.date)
    }
    val formattedDate = remember {
        dateConverter(previous.date)
    }
    var openVideoPlayer by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = modifier
            .padding(8.dp)
            .shadow(
                elevation = 10.dp,
                ambientColor = Color.Gray,
                spotColor = Color.Black,
                shape = RoundedCornerShape(30.dp),
            ),
        backgroundColor = MaterialTheme.colors.background,
        shape = RoundedCornerShape(30.dp),
        elevation = 10.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = previous.winner,
                style = MaterialTheme.typography.h6.copy(
                    color = Color.Cyan
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$formattedTime  |  $formattedDate",
                style = MaterialTheme.typography.h6.copy(
                    fontSize = 13.sp,
                    fontStyle = FontStyle.Normal
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = previous.home,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "VS",
                    fontSize = 18.sp,
                    modifier = Modifier.padding(4.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = previous.away,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Away",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Chip(
                onClick = { openVideoPlayer = true },
                colors = ChipDefaults.chipColors(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = Gold
                ),
                border = BorderStroke(
                    ChipDefaults.OutlinedBorderSize,
                    Gold
                ),
                leadingIcon = {
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "Highlights"
                    )
                }
            ) {
                Text(text = "Highlights")
            }
        }
    }

    if (openVideoPlayer) VideoPlayerScreen(
        videoUri = previous.highlights,
        videoDescription = previous.description
    ) {
        openVideoPlayer = false
    }

}
