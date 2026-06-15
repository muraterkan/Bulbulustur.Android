package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class CompanyPaymentTermInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val SystemDescPaymentTermId: Int = 0
)
