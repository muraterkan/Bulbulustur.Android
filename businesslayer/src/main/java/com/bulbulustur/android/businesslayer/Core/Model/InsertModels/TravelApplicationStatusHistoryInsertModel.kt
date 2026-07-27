package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelApplicationStatusHistoryInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelApplicationId: Int,
    val oldTravelApplicationStatusId: Int?,
    val newTravelApplicationStatusId: Int,
    val changedByMemberId: Int?,
    val description: String
)