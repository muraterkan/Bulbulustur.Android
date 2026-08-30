package com.bulbulustur.android.businesslayer.Core.DTO

data class WholesaleProductCategorySupplierDTO(
    val WholesaleProductCategorySupplierId: Int = 0,
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val CompanyName: String = "",
    val Content: String = "",
    val Picture: String? = null,
    val ProductCategoryId: Int = 0,
    val Name: String? = null,
    val Logo: String = "",
    val BusinessType: String = "",
    val SupplierSomeProduct: List<WholesaleProductCategorySupplierSomeProductDTO> = emptyList()
)

data class WholesaleProductCategorySupplierSomeProductDTO(
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val DefaultPicture: String = ""
)
