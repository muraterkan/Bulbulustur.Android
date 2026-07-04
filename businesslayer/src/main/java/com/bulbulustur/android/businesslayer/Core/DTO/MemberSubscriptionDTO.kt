package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberSubscriptionDTO(
    val MemberSubscriptionId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SubscriptionPlanTypeId: Int = 0,
    val SubscriptionTypeId: Int = 0,
    val StartDate: String = "",
    val EndDate: String = "",
    val PlanPrice: Double = 0.0,
    val SubscriptionTypeName: String = "",
    val SubscriptionPlanTypeName: String = "",
    val CurrencySymbol: String = "",
    val MemberName: Any? = null,
    val Subscription: String = ""
)