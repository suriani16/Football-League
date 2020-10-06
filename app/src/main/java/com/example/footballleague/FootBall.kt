package com.example.footballleague

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootBall(val leagueName: String, val leagueImage: Int, val leagueDescription: String) :
    Parcelable
