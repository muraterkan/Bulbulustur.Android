package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Models.BreadcrumbModel
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

data class B2CProductDataDTO(
    val Products2: PaginatedList<B2CProductData> = PaginatedList(),
    val Products: List<B2CProductData> = emptyList(),
    val Categories: List<ProductCategoryDTO> = emptyList(),
    val SubCategories: List<ProductCategoryDTO> = emptyList(),
    val Brands: List<ProductBrandDTO> = emptyList(),
    val Breadcrumbs: List<BreadcrumbModel> = emptyList(),
    val CategoryName: String = "",
    val CategoryDescription: String = "",
    val CategoryDescriptionPicture: String = "",
    val SeoTitle: String = "",
    val SeoDescription: String = "",
    val StoreId: Int = 0,
    val Colors: List<SystemDescColorDTO> = emptyList(),
    val Sizes: List<SystemDescSizeTypeDTO> = emptyList()
)

data class B2CProductData(
    val ProductId: Int = 0,
    val VariantId: Int = 0,
    val ProductVariantPriceId: Int = 0,
    val ProductName: String = "",
    val ProductCategoryId: Int = 0,
    val CategoryName: String = "",
    val BrandId: Int = 0,
    val BrandName: String = "",
    val InsertedDate: String = "",
    val DefaultPicture: String = "",
    val Price: Double = 0.0,
    val Stock: Int = 0,
    val CurrencySymbol: String = "",
    val StoreId: Int = 0,
    val ColorId: Int = 0,
    val SizeId: Int = 0
)

data class B2CProductFilterDTO(
    val ProductCategoryId: Int = 0,
    val ChildIds: String = "",
    val Categories: List<Int> = emptyList(),
    val BrandIds: List<String> = emptyList(),
    val MinPrice: Int? = null,
    val MaxPrice: Int? = null,
    val SortOrder: String = "Name_Desc",
    val LanguageId: Int = 1,
    val ColorIds: List<String> = emptyList(),
    val SizeIds: List<String> = emptyList()
)