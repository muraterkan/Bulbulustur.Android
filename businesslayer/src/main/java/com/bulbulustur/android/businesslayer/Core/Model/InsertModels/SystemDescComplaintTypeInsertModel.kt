package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescComplaintTypeInsertModel(
    val employeeId: Int,
    val insertedDate: String,
    val statusId: Int,
    val content: String
)