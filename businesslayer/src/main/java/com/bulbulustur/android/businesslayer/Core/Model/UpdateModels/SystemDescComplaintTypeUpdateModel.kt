package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescComplaintTypeUpdateModel(
    val systemDescComplaintTypeId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)