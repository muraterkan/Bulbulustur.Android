package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescReturnRequestStatusUpdateModel(
    val systemDescReturnRequestStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)