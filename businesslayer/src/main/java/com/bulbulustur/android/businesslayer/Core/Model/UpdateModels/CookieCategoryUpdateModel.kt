package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CookieCategoryUpdateModel(
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
