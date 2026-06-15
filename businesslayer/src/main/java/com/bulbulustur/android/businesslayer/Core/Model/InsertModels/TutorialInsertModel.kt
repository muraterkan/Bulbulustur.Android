package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class TutorialInsertModel(
    val EmployeeId: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ApplicationId: Int = 0,
    val ProjectId: Int = 0,
    val CategoryId: Int = 0,
    val AuthorId: Int = 0,
    val VideoLink: String = "",
    val Thumbnail: String = ""
)
