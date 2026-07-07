package com.bulbulustur.android.businesslayer.Core.Model.InsertModels

data class WholesaleHomepageFeaturedProductInsertModel(
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val CompanyId: Int = 0,
    val SortOrder: Int = 0,
    val StartingDate: String? = null,
    val EndingDate: String? = null,
    val IsManual: Boolean = false
)
