package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyVerificationUpdateModel(
    val VerificationId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val Description: String = "",
    val ReportDate: String = "",
    val ValidityDate: String = "",
    val AssessorName: String = ""
)
