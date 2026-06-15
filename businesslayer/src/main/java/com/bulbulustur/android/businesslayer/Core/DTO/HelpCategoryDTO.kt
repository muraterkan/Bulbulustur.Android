package com.bulbulustur.android.businesslayer.Core.DTO

data class HelpCategoryDTO(
    val HelpCategoryId: Int = 0,
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ParentId: Int = 0,
    val Content: String = "",
    val Sorting: Int = 0,
    val ApplicationId: Int = 0,
    val LanguageId: Int = 0
)
