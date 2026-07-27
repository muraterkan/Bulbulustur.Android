package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class PropertyListingUpdateModel(
    val propertyListingId: Int,
    val propertyId: Int,
    val propertyRoomId: Int,
    val memberId: Int,
    val listingStatusId: Int,
    val visibilityTypeId: Int,
    val title: String,
    val publicDescription: String?,
    val isPublished: Boolean,
    val firstPublishedDate: String?,
    val currentPublicationStartDate: String?,
    val currentPublicationEndDate: String?,
    val completedDate: String?,
    val lastDeactivatedDate: String?,
    val viewCount: Int,
    val lastBoostedDate: String?,
    val nextBoostAvailableDate: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)