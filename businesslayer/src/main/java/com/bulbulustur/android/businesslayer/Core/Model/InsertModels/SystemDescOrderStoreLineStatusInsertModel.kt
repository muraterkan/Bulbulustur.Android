package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescOrderStoreLineStatusInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)