package com.bulbulustur.android.businesslayer.Core.DTO

data class ReturnRequestDTO(
    val ReturnRequestId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val OrderLineId: Int = 0,
    val HaveInvoice: Int = 0,
    val HaveAccessory: Int = 0,
    val ReturnRequestReasonId: Int = 0,
    val Description: String = "",
    val ReturnRequestStatusId: Int = 0,
    val Price: Double? = null,
    val CurrencyId: Int = 0,
    val ReturnKey: String = ""
)
