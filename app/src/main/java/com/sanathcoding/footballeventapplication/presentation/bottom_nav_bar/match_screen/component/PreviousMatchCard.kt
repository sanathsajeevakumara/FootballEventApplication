package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanathcoding.footballeventapplication.core.util.dateConverter
import com.sanathcoding.footballeventapplication.core.util.timeConverter
import com.sanathcoding.footballeventapplication.domain.model.match.Previous

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
//        backgroundColor = color,
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
                style = MaterialTheme.typography.h6,
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
            Text(
                text = "Click to see the highlights",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable {
                    openVideoPlayer = true
                }
            )
        }
    }

    if (openVideoPlayer) VideoPlayerScreen(
        videoUri = previous.highlights,
        videoDescription = previous.description
    ) {
        openVideoPlayer = false
    }

}
