package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescInvoiceTypeUpdateModel(
    val systemDescInvoiceTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)