package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class TravelBoosterUpdateModel(
    val travelBoosterId: Int,
    val travelId: Int,
    val memberId: Int,
    val sourceType: String,
    val paymentId: Int?,
    val memberSubscriptionId: Int?,
    val price: Double?,
    val currencyId: Int?,
    val purchasedDate: String,
    val usedDate: String?,
    val isUsed: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)