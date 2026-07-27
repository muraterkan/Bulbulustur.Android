package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberLanguageInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val MemberId: Int = 0,
    val LanguageId: Int = 0,
    val LanguageLevelId: Int = 0
)
