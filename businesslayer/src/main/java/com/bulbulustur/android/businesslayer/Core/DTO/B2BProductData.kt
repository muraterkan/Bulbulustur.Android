package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Models.BreadcrumbModel
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

data class B2BProductDataDTO(
    val Products2: PaginatedList<B2BProductData> = PaginatedList(),
    val Products: List<B2BProductData> = emptyList(),
    val Categories: List<ProductCategoryDTO> = emptyList(),
    val SubCategories: List<ProductCategoryDTO> = emptyList(),
    val Brands: List<ProductBrandDTO> = emptyList(),
    val Breadcrumbs: List<BreadcrumbModel> = emptyList(),
    val CategoryName: String = "",
    val CategoryDescription: String = "",
    val CategoryDescriptionPicture: String = "",
    val SeoTitle: String = "",
    val SeoDescription: String = ""
)

data class B2BProductData(
    val WholesaleProductId: Int = 0,
    val ProductName: String = "",
    val ProductCategoryId: Int = 0,
    val CategoryName: String = "",
    val BrandId: Int = 0,
    val BrandName: String = "",
    val InsertedDate: String = "",
    val DefaultPicture: String = "",
    val Picture: String = "",
    val Price: Double = 0.0,
    val MinimumOrderQuantity: Int = 0,
    val MinimumOrderUnit: String = "",
    val CompanyId: Int = 0,
    val CompanyName: String = "",
    val Logo: String = "",
    val StoreId: Int = 0,
    val Rating: Double = 0.0
)
