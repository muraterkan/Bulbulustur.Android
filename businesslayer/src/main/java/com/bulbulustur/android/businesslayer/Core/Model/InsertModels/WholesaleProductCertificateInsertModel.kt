package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleProductCertificateInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val QualityCertificateTypeId: Int? = null,
    val Sorting: Int = 0
)
