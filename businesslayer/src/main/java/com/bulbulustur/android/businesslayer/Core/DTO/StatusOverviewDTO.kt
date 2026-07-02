package com.bulbulustur.android.businesslayer.Core.DTO

data class StatusOverviewDTO(
    val OverallState: String = "",
    val OverallStateText: String = "",
    val OverallStateColorCode: String = "",
    val LastCheckedDate: String = "",
    val Last90DaysUptimePercentage: Double = 0.0,
    val Components: List<StatusComponentDTO> = emptyList(),
    val ActiveIncidents: List<StatusIncidentDTO> = emptyList(),
    val ScheduledMaintenances: List<StatusMaintenanceDTO> = emptyList(),
    val HistoryIncidents: List<StatusIncidentDTO> = emptyList()
)