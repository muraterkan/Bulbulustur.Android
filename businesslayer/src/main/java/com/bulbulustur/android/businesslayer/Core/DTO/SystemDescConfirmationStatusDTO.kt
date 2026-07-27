package com.bulbulustur.android.businesslayer.Core.DTO

data class SystemDescConfirmationStatusDTO(
    val confirmationStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content1: String,
    val badge: String
)