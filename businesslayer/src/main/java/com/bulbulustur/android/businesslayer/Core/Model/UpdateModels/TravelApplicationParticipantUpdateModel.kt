package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelApplicationParticipantUpdateModel(
    val travelApplicationParticipantId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelApplicationId: Int,
    val memberId: Int,
    val isPrimary: Boolean
)