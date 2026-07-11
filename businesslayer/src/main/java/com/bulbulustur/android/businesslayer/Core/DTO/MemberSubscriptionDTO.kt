package com.bulbulustur.android.businesslayer.Core.DTO

data class MemberSubscriptionDTO(
    val MemberSubscriptionId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String? = null,
    val StatusId: Int = 0,
    val SubscriptionPlanTypeId: Int = 0,
    val SubscriptionTypeId: Int = 0,
    val StartDate: String? = null,
    val EndDate: String? = null,
    val PlanPrice: Double = 0.0,
    val SubscriptionTypeName: String? = null,
    val SubscriptionPlanTypeName: String? = null,
    val CurrencySymbol: String? = null,
    val MemberName: Any? = null,
    val Subscription: String? = null
)