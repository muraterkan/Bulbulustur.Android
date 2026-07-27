package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelOfferStatusHistoryDTO(
    val travelOfferStatusHistoryId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelOfferId: Int,
    val oldTravelOfferStatusId: Int?,
    val newTravelOfferStatusId: Int,
    val changedByMemberId: Int?,
    val description: String
)