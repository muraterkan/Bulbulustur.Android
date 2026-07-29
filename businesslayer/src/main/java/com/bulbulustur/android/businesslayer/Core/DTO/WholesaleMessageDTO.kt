package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleMessageDTO(
    val WholesaleMessageId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MessageThreadId: Int = 0,
    val Body: String = "",

    val SenderName: String? = null,
    val RecipientName: String? = null,

    val IsRead: Boolean = false,
    val IsDeleted: Boolean = false,
    val IsStarred: Boolean = false,
    val IsPriority: Boolean = false,

    val SenderId: Int = 0,
    val RecipientId: Int = 0,

    val SenderEmail: String? = null,

    val IsTrash: Boolean = false,
    val IsArchived: Boolean = false,

    val CompanyId: Int = 0,

    val SenderSurname: String? = null,
    val SenderFullName: String? = null,
    val RecipientSurname: String? = null,
    val RecipientFullName: String? = null
)
