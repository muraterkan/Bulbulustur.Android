package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelApplicationParticipantInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelApplicationId: Int,
    val memberId: Int,
    val isPrimary: Boolean
)