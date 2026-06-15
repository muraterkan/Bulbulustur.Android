package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class HelpCategoryInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ParentId: Int = 0,
    val Content: String = "",
    val Sorting: Int = 0,
    val ApplicationId: Int = 0,
    val LanguageId: Int = 0
)
