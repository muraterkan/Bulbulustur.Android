package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelOfferParticipantDTO(
    val travelOfferParticipantId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelOfferId: Int,
    val memberId: Int,
    val isPrimary: Boolean
)