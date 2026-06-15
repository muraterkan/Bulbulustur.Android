package com.bulbulustur.android.businesslayer.Core.Model.UpdateModels

data class StoreUpdateModel(
    val StoreId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val StoreName: String = "",
    val StoreDescription: String = "",
    val CompanyId: Int = 0,
    val Rating: Double = 0.0,
    val VocationMode: Int = 0,
    val ReviewNumber: Int = 0,
    val Uuid: String = "",
    val Picture: String = "",
    val HeaderBackgroundColor: String = "",
    val HeaderBackgroundPicture: String = "",
    val DefaultEstimatedShippingTime: Int = 0,
    val DefaultVatRateId: Int = 0,
    val DefaultDesi: Int = 0,
    val StoreKey: String = "",
    val ArchiveCreated: Boolean = false,
    val ArchiveFolderNo: String = ""
)
