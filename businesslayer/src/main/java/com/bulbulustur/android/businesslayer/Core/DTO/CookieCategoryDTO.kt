package com.bulbulustur.android.businesslayer.Core.DTO

data class CookieCategoryDTO(
    val CookieCategoryId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Guid: String = "",
    val CategoryCode: String = "",
    val IsRequired: Boolean = false,
    val DefaultEnabled: Boolean = false,
    val DisplayOrder: Int = 0,
    val LastUpdated: String = ""
)
