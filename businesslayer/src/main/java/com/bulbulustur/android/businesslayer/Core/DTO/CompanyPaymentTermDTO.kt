package com.bulbulustur.android.businesslayer.Core.DTO

data class CompanyPaymentTermDTO(
    val CompanyPaymentTermId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val SystemDescPaymentTermId: Int = 0,
    val QualityCertificateType: String = "",
    val Content: String = "",
    val Symbol: String = ""
)