package com.bulbulustur.android.businesslayer.Core.DTO

data class BannerDTO(
    val BannerId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val BackgroundPicture: String = "",
    val BackgroundClass: String = "",
    val TextColor: String = "",
    val DisplayOrder: Int = 0,
    val BannerType: Int = 0,
    val PrimaryButtonLink: String = "",
    val PrimaryButtonClass: String = "",
    val SecondaryButtonLink: String = "",
    val SecondaryButtonClass: String = ""
)
