package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelOfferParticipantUpdateModel(
    val travelOfferParticipantId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelOfferId: Int,
    val memberId: Int,
    val isPrimary: Boolean
)