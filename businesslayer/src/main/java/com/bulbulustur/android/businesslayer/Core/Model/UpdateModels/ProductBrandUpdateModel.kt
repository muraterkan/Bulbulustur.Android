package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductBrandUpdateModel(
    val BrandId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Brand: String = "",
    val Picture: String = "",
    val BrandPage: Boolean = false,
    val CoverPicture: String = "",
    val FoundedYear: Short? = null,
    val CountryId: Int? = null,
    val Website: String = "",
    val IsFeatured: Boolean = false,
    val SortOrder: Int = 0
)
