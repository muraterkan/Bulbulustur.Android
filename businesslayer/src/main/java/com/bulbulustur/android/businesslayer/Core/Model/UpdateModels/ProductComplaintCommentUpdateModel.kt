package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductComplaintCommentUpdateModel(
    val ComplaintCommentId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ComplaintId: Int = 0,
    val EmployeeId: Int = 0,
    val Comment: String = ""
)
