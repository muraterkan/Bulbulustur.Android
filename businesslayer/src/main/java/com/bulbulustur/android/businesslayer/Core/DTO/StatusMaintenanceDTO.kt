package com.bulbulustur.android.businesslayer.Core.DTO

data class StatusMaintenanceDTO(
    val StatusMaintenanceId: Int = 0,
    val Slug: String = "",
    val Title: String = "",
    val Description: String = "",
    val MaintenanceStateId: Int = 0,
    val MaintenanceStateName: String = "",
    val MaintenanceStateColorCode: String = "",
    val ScheduledStart: String = "",
    val ScheduledEnd: String = "",
    val CompletedDate: String? = null,
    val IsPublic: Boolean = false
)