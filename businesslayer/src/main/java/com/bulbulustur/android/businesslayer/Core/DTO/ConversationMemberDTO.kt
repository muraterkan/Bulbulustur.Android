package com.bulbulustur.android.businesslayer.Core.DTO

data class ConversationMemberDTO(
    val conversationMemberId: Int,
    val conversationId: Int,
    val memberId: Int,
    val joinedDate: String,
    val leftDate: String?,
    val lastReadMessageId: Int?,
    val lastReadDate: String?,
    val isMuted: Boolean,
    val isBlocked: Boolean,
    val isArchived: Boolean,
    val employeeId: Int,
    val insertedDate: String,
    val updatedDate: String?,
    val statusId: Int
)