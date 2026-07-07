package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductHomepageSpecialContentDTO(
    val ProductHomepageSpecialContentId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val SpecialContentGroupId: Int = 0,
    val ProductSpecialGroupId: Int = 0,
    val GroupName: String = "",
    val Products: List<ProductHomepageSpecialDTO> = emptyList()
)
