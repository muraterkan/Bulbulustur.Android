package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescCertificateTypeInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String,
    val logo: String
)