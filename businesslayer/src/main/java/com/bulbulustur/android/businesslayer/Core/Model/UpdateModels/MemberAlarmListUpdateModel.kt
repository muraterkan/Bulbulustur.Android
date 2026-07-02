package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberAlarmListUpdateModel(
    val MemberAlarmListId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val ProductId: Int = 0,
    val Note: String = "",
    val StoreId: Int = 0,
    val VariantId: Int = 0,
    val CurrencyId: Int? = null,
    val Processed: Boolean = false,
    val BuyerAcceptsOtherSellers: Boolean = false,
    val TriggerCheck: Boolean = false
)