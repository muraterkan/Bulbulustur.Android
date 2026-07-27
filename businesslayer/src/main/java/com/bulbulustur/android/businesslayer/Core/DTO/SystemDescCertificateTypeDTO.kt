package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescCertificateTypeDTO(
    val systemDescCertificateTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String,
    val logo: String
)