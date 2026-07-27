package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescConfirmationStatusInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content1: String,
    val badge: String
)