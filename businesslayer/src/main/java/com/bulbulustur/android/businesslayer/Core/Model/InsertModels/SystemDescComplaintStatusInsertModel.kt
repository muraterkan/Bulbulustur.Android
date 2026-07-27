package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescComplaintStatusInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)