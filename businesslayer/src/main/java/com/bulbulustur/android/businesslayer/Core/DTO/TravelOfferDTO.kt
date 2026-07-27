package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelOfferDTO(
    val travelOfferId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val travelId: Int,
    val offererMemberId: Int,
    val travelPartyTypeId: Int,
    val tripOfferStatusId: Int,
    val offerStartDate: String?,
    val offerEndDate: String?,
    val isDateFlexible: Boolean,
    val description: String,
    val offerExpiresDate: String?,
    val viewedDate: String?,
    val respondedDate: String?,
    val withdrawnDate: String?
)