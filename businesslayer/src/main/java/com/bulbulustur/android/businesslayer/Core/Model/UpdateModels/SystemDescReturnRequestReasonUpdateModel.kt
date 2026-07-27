package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescReturnRequestReasonUpdateModel(
    val systemDescReturnRequestReasonId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)