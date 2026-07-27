package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescCargoDesiPriceInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val cargoCompanyId: Int,
    val systemDescDesiId: Int,
    val netPrice: Double,
    val grossPrice: Double?,
    val content: String
)