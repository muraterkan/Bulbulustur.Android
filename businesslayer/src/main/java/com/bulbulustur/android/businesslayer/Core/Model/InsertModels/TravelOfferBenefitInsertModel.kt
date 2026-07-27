package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TravelOfferBenefitInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelOfferId: Int,
    val travelOfferBenefitTypeId: Int,
    val estimatedAmount: Double?,
    val currencyId: Int?,
    val isFullyCovered: Boolean,
    val description: String
)