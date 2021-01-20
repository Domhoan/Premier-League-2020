package com.sun.premierleague.data.source

import com.example.premier_league.data.model.*
import com.example.premier_league.data.source.remote.OnDataLoadedCallback

interface FootballDataSource {
    interface Local {
        fun getFootballNotifications(
            callback: OnDataLoadedCallback<List<MatchNotification>>
        )

        fun getItemFootballNotification(
            idMatch: String,
            callback: OnDataLoadedCallback<MatchNotification>
        )

        fun addFootballNotification(
            matchNotification: MatchNotification,
            callback: OnDataLoadedCallback<Boolean>
        )

        fun deleteFootballNotification(
            idMatch: String,
            callback: OnDataLoadedCallback<Boolean>
        )
    }

    interface Remote {
        fun getMatch(time: Time, callback: OnDataLoadedCallback<List<MatchItem>>)

        fun getTeam(idTeam: String, callback: OnDataLoadedCallback<List<TeamItem>>)

        fun getStat(callback: OnDataLoadedCallback<List<StatItem>>)
    }
}
