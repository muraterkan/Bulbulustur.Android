package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescComplaintStatusUpdateModel(
    val systemDescComplaintStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)