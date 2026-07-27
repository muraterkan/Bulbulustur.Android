package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ConversationInsertModel(
    val createdByMemberId: Int,
    val sourceType: String,
    val sourceId: Int?,
    val title: String?,
    val startedDate: String,
    val lastMessageDate: String?,
    val isClosed: Boolean,
    val closedDate: String?,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)