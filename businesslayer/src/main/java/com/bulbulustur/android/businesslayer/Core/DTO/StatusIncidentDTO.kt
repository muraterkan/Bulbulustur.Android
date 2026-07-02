package com.bulbulustur.android.businesslayer.Core.DTO

data class StatusIncidentDTO(
    val StatusIncidentId: Int = 0,
    val IncidentKey: String = "",
    val Slug: String = "",
    val Title: String = "",
    val Summary: String = "",
    val SeverityId: Int = 0,
    val SeverityName: String = "",
    val SeverityColorCode: String = "",
    val IncidentStateId: Int = 0,
    val IncidentStateName: String = "",
    val IncidentStateColorCode: String = "",
    val StartedDate: String = "",
    val ResolvedDate: String? = null,
    val IsPublic: Boolean = false,
    val Updates: List<StatusIncidentTimelineDTO> = emptyList(),
    val AffectedComponents: List<StatusComponentDTO> = emptyList()
)