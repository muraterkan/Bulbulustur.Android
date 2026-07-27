package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescCertificateTypeUpdateModel(
    val systemDescCertificateTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String,
    val logo: String
)