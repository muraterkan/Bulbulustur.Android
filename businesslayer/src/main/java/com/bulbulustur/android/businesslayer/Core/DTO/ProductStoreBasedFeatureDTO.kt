package com.bulbulustur.android.businesslayer.Core.DTO

data class ProductStoreBasedFeatureDTO(
    val ProductStoreBasedFeatureId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val ProductId: Int = 0,
    val StoreId: Int = 0,
    val ProductSecureKey: String = "",
    val ShippingDuration: Int = 0,
    val ShippingAddressId: Int = 0,
    val ReturnAddressId: Int = 0,
    val CommissionRate: Int = 0
)
