package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class ProductComplaintUpdateModel(
    val ComplaintId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val ComplaintTypeId: Int = 0,
    val Description: String = "",
    val ComplaintStatusId: Int = 0,
    val PlatformType: Int = 0
)
