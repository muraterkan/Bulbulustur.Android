package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class MemberTempInsertModel(
    val Email: String = "",
    val ActivationCode: String = "",
    val ExpirationDate: String = "",
    val InsertedDate: String = "",
    val StatusId: Int = 0
)