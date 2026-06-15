package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class ProductComplaintCommentInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ComplaintId: Int = 0,
    val EmployeeId: Int = 0,
    val Comment: String = ""
)
