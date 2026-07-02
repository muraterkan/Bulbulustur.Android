package com.bulbulustur.android.businesslayer.Core.DTO

data class StatusIncidentTimelineDTO(
    val StatusIncidentTimelineId: Int = 0,
    val StatusIncidentId: Int = 0,
    val UpdateStateId: Int = 0,
    val UpdateStateName: String = "",
    val UpdateStateColorCode: String = "",
    val UpdateTitle: String = "",
    val UpdateMessage: String = "",
    val UpdateDate: String = "",
    val IsPublic: Boolean = false
)