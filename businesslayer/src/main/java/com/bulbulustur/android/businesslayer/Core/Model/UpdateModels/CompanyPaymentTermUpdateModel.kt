package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class CompanyPaymentTermUpdateModel(
    val CompanyPaymentTermId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val SystemDescPaymentTermId: Int = 0
)
