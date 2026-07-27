package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelOfferParticipantInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelOfferId: Int,
    val memberId: Int,
    val isPrimary: Boolean
)