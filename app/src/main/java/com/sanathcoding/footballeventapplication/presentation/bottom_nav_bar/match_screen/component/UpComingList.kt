package com.sanathcoding.footballeventapplication.presentation.bottom_nav_bar.match_screen.component

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.sanathcoding.footballeventapplication.domain.model.match.Upcoming


fun LazyListScope.upComingList(upComings: List<Upcoming>?) {
    upComings?.let {
        items(upComings) { upComing ->
            UpComingMatchCard(upcoming = upComing)
        }
    }
}