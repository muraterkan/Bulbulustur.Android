package com.bulbulustur.android.businesslayer.Core.DTO

data class TravelDTO(
    val travelId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int,
    val memberId: Int,
    val travelTypeId: Int,
    val travelEngagementModeId: Int,
    val travelStatusId: Int,
    val travelPartyTypeId: Int,
    val travelExpensePolicyId: Int,
    val countryId: Int,
    val cityId: Int?,
    val travelStartDate: String,
    val travelEndDate: String,
    val isDateFlexible: Boolean,
    val title: String,
    val description: String,
    val publishedDate: String?,
    val listingExpiresDate: String?,
    val unregisteredGuestCount: Int,
    val lastBoostedDate: String?,
    val nextBoostAvailableDate: String?
)