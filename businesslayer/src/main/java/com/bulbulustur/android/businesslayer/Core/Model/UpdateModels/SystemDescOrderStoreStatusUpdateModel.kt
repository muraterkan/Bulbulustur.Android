package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class SystemDescOrderStoreStatusUpdateModel(
    val systemDescOrderStoreStatusId: Int,
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)