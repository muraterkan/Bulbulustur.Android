package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelOfferStatusHistoryUpdateModel(
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