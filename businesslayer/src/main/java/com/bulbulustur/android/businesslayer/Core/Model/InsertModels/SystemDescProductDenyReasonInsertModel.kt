package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class SystemDescProductDenyReasonInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val Content: String = "",
    val Suggestion: String = "",
    val Sorting: Int? = null
)
