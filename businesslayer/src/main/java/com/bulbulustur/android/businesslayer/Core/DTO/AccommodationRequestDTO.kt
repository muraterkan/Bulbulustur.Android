package com.bulbulustur.android.businesslayer.Core.DTO

data class AccommodationRequestDTO(
    val accommodationRequestId: Int,
    val memberId: Int,
    val roomTypeId: Int?,
    val moveUrgencyTypeId: Int?,
    val visibilityTypeId: Int,
    val minimumBudget: Double?,
    val maximumBudget: Double?,
    val currencyId: Int,
    val preferredMoveDate: String?,
    val isMoveDateFlexible: Boolean,
    val minimumStayMonth: Int?,
    val maximumStayMonth: Int?,
    val requestDescription: String,
    val isActive: Boolean,
    val publishedDate: String?,
    val expiresDate: String?,
    val lastConfirmedDate: String?,
    val adultCount: Int,
    val childCount: Int,
    val petCount: Int,
    val isCoupleApplication: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)