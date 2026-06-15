package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductCategoryGuideDTO(
    val ProductCategoryGuideId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductCategoryId: Int = 0,
    val Picture: String = "",
    val CoverPicture: String = "",
    val IconClass: String = "",
    val Sort: Int = 0,
    val IsAiGenerated: Boolean = false,
    val IsLocked: Boolean = false
)
