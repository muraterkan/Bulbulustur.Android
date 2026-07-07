package com.bulbulustur.android.businesslayer.Core.DTO

data class CompanyVerificationSummaryDTO(
    val Verifications: List<CompanyVerificationDTO> = emptyList(),
    val TotalYearsValid: Int = 0,
    val VerificationStatus: String = ""
)