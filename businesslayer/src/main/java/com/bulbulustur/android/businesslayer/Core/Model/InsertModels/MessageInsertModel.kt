package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MessageInsertModel(
    val conversationId: Int,
    val senderMemberId: Int,
    val messageTypeId: Int,
    val content: String?,
    val mediaUrl: String?,
    val replyToMessageId: Int?,
    val sentDate: String,
    val editedDate: String?,
    val deletedDate: String?,
    val isEdited: Boolean,
    val isDeleted: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)