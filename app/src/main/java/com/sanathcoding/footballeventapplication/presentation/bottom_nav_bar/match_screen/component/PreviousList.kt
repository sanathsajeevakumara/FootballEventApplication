package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.sanathcoding.footballeventapplication.domain.model.match.Previous

@Composable
fun PreviousList(previousList: List<Previous>?) {
    previousList?.let {
        LazyRow(content = {
            items(it) { previous ->
                PreviousMatchCard(
                    previous = previous,
                )
            }
        })
    }
}