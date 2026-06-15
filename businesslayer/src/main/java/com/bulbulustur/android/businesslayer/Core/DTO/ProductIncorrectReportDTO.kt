package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductIncorrectReportDTO(
    val IncorrectReportId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val VariantId: Int = 0,
    val ProductId: Int = 0,
    val IncorrectReportTypeId: Int = 0,
    val Description: String = ""
)
