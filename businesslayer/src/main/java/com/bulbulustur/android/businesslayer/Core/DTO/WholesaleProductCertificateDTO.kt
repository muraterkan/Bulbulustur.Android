package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleProductCertificateDTO(
    val WholesaleProductCertificateId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val QualityCertificateTypeId: Int? = null,
    val Sorting: Int = 0
)
