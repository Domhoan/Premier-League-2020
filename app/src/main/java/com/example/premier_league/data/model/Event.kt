package com.example.premier_league.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    var goalScorers: List<Goalscorer>,
    var cards: List<Card>,
    var substitutions: Substitutions
) : Parcelable
