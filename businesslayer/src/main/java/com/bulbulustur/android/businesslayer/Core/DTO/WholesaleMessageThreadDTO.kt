package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleMessageThreadDTO(
    val WholesaleMessageThreadId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SenderId: Int = 0,
    val RecipientId: Int = 0
)
