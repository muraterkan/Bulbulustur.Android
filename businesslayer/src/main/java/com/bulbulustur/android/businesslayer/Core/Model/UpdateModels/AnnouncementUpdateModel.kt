package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class AnnouncementUpdateModel(
    val AnnouncementId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val Content: String = "",
    val BackgroundImageUrl: String = "",
    val CityId: Int = 0
)
