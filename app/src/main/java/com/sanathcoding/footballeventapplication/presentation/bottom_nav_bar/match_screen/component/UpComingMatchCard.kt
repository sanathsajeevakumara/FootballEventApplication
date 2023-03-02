package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
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

    var showReminderPopUp by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    var hasNotificationPermission by remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d("ggg", "Above API level 33")
            mutableStateOf(
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            )
        } else {
            Log.d("ggg", "Below API level 33")
            mutableStateOf(true)
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasNotificationPermission = isGranted
        }
    )

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
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
            UpComingTeamDetail(upcoming, formattedTime, formattedDate)

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