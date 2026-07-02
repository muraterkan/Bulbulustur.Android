package com.bulbulustur.android.businesslayer.Core.DTO

data class StatusComponentDTO(
    val StatusComponentId: Int = 0,
    val StatusComponentGroupId: Int = 0,
    val ComponentKey: String = "",
    val PublicName: String = "",
    val InternalName: String = "",
    val Description: String = "",
    val GroupName: String = "",
    val CurrentStateId: Int = 0,
    val CurrentStateKey: String = "",
    val CurrentStateName: String = "",
    val CurrentStateColorCode: String = "",
    val Last90DaysUptimePercentage: Double = 0.0,
    val AverageResponseTimeMs: Int? = null,
    val DisplayOrder: Int = 0,
    val IsActive: Boolean = false,
    val IsPublic: Boolean = false,
    val LastCheckedDate: String? = null,
    val LastHttpStatusCode: Int? = null,
    val LastResponseTimeMs: Int? = null,
    val LastErrorMessage: String? = null
)