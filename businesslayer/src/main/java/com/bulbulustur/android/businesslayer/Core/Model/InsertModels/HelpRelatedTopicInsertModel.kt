package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class HelpRelatedTopicInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val HelpId: Int = 0,
    val RelatedHelpId: Int = 0,
    val Sorting: Int = 0
)
