package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelOfferStatusHistoryInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val travelOfferId: Int,
    val oldTravelOfferStatusId: Int?,
    val newTravelOfferStatusId: Int,
    val changedByMemberId: Int?,
    val description: String
)