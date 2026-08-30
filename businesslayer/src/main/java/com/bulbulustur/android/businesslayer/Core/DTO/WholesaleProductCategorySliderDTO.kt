package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleProductCategorySliderDTO(
    val WholesaleProductCategorySliderId: Int = 0,
    val StatusId: Int = 0,
    val BackgroundPicture: String = "",
    val ProductCategoryId: Int = 0,
    val Pages: List<WholesaleProductCategorySliderPageDTO> = emptyList()
)
