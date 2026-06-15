package com.bulbulustur.android.businesslayer.Core.DTO

data class CompanyCertificateDTO(
    val CompanyCertificateId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val CompanyId: Int = 0,
    val StatusId: Int = 0,
    val QualityCertificateTypeId: Int? = null,
    val Description: String = "",
    val Picture: String = "",
    val Sorting: Int = 0,
    val ExpiryDate: String = "",
    val CertificateNumber: String = ""
)
