package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductBrandSectionDTO(
    val ProductBrandSectionId: Int = 0,

    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,

    val BrandId: Int = 0,
    val Title: String = "",
    val BackgroundType: String = "",
    val BackgroundValue: String = "",
    val FullWidth: Boolean = false,
    val PaddingTop: Int = 0,
    val PaddingBottom: Int = 0,
    val OrderNo: Int = 0,
    val SectionTypeId: Int = 0,
    val CategoryId: Int? = null,

    val LanguageId: Int = 0,
    val Content: String = "",
    val Pages: List<ProductBrandSectionPageDTO> = emptyList(),
    val SectionTypeCode: String = "",
    val SectionTypeName: String = ""
)