package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class PropertyContactRequestUpdateModel(
    val propertyContactRequestId: Int,
    val propertyListingId: Int,
    val senderMemberId: Int,
    val receiverMemberId: Int,
    val contactRequestStatusId: Int,
    val firstMessage: String,
    val viewedDate: String?,
    val respondedDate: String?,
    val cancelledDate: String?,
    val conversationId: Int?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)