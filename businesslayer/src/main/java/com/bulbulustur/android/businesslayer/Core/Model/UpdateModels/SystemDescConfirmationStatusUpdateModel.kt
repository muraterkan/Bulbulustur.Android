package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescConfirmationStatusUpdateModel(
    val confirmationStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content1: String,
    val badge: String
)