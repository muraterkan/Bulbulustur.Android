package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelApplicationStatusHistoryDTO(
    val travelApplicationStatusHistoryId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelApplicationId: Int,
    val oldTravelApplicationStatusId: Int?,
    val newTravelApplicationStatusId: Int,
    val changedByMemberId: Int?,
    val description: String
)