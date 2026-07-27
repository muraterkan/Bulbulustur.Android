package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class PropertyListingPublicationInsertModel(
    val propertyListingId: Int,
    val memberId: Int,
    val publicationType: String,
    val durationDay: Int,
    val paymentId: Int?,
    val price: Double,
    val currencyId: Int,
    val startedDate: String,
    val expiresDate: String,
    val isActive: Boolean,
    val deactivatedDate: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)