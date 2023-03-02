package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sanathcoding.footballeventapplication.R
import com.sanathcoding.footballeventapplication.core.util.showToast
import com.sanathcoding.footballeventapplication.data.remote.repository.AndroidAlarmScheduler
import com.sanathcoding.footballeventapplication.domain.model.AlarmItem
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming
import java.time.LocalDateTime

@Composable
fun SetReminderPopUp(
    upcoming: Upcoming,
    onDismiss: () -> Unit
) {

    val context = LocalContext.current
    val scheduler = AndroidAlarmScheduler(LocalContext.current)
    var alarmItem: AlarmItem?

    var secondsText by remember {
        mutableStateOf("")
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 4.dp,
            border = BorderStroke(5.dp, Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.reminder_title),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = secondsText,
                    onValueChange = { secondsText = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = stringResource(R.string.alarm_in_sec))
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        alarmItem = AlarmItem(
                            time = LocalDateTime.now()
                                .plusSeconds(secondsText.toLong()),
                            message = upcoming.description
                        )
                        alarmItem?.let (scheduler::scheduler)
                        secondsText = ""
                        onDismiss()
                        context.showToast("Reminder Added Successfully")
                    }) {
                        Text(text = stringResource(R.string.add))
                    }
                }
            }
        }
    }
}