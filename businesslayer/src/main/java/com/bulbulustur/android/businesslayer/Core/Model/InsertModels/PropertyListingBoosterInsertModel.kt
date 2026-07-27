package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class PropertyListingBoosterInsertModel(
    val propertyListingId: Int,
    val memberId: Int,
    val sourceType: String,
    val paymentId: Int?,
    val propertyListingPublicationId: Int?,
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