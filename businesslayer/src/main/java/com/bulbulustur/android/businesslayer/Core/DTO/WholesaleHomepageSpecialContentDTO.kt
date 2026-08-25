package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleHomepageSpecialContentDTO(
    val WholesaleHomepageSpecialContentId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductSpecialGroupId: Int = 0,
    val GroupName: String = "",
    val ContentName: String = "",
    val Products: List<WholesaleHomepageSpecialDTO> = emptyList()
)