package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleMessageDTO(
    val WholesaleMessageId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MessageThreadId: Int = 0,
    val Body: String = "",
    val SenderName: String = "",
    val RecipientName: String = "",
    val IsRead: Boolean = false,
    val IsDeleted: Boolean = false,
    val IsStarred: Boolean = false,
    val IsPriority: Boolean = false,
    val SenderId: Int = 0,
    val RecipientId: Int = 0,
    val SenderEmail: String = "",
    val IsTrash: Boolean = false,
    val IsArchived: Boolean = false,
    val CompanyId: Int = 0,
    val SenderSurname: String = "",
    val SenderFullName: String = "",
    val RecipientSurname: String = "",
    val RecipientFullName: String = ""
)
