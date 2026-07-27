package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescOrderStoreLineStatusUpdateModel(
    val systemDescOrderStoreLineStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)