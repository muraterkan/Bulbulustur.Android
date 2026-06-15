package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class MemberCouponUpdateModel(
    val MemberCouponId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val CouponCode: String = "",
    val Amount: Double = 0.0,
    val LastUsingDate: String = "",
    val Descripion: String = "",
    val UpAmount: Double = 0.0,
    val Sender: Int = 0,
    val Used: Int = 0,
    val UsedDate: String = "",
    val OrderId: String = "",
    val CouponStatusId: Int = 0
)
