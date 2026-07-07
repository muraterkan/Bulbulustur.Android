package com.bulbulustur.android.businesslayer.Core.DTO

import com.bulbulustur.android.businesslayer.Core.Models.BreadcrumbModel

data class WholesaleProductDTO(
    val WholesaleProductId: Int = 0,
    val InsertedBy: Int = 0,
    val InsertedDate: String = "",
    val StatusId: Int = 0,
    val CompanyId: Int = 0,
    val ProductCategoryId: Int = 0,
    val ProductName: String = "",
    val Description: String = "",
    val BrandId: Int = 0,
    val MinimumOrderQuantity: Int = 0,
    val MinimumOrderUnitId: Int = 0,
    val MonthlyProduction: Int = 0,
    val MonthlyProductionUnitId: Int = 0,
    val ViewCount: Int = 0,
    val SeoTitle: String = "",
    val SeoDescription: String = "",
    val ConfirmationTypeId: Int = 0,
    val LeadTimeId: Int = 0,
    val ModelNumber: String = "",
    val CustomizationId: Int = 0,
    val DimensionsPerUnit: String = "",
    val WeightPerUnit: Double = 0.0,
    val HtsCode: String = "",
    val OriginId: Int = 0,
    val DimensionsPerUnitId: Int = 0,
    val WeightPerUnitId: Int = 0,
    val SamplePrice: Double = 0.0,

    val CompanyName: String = "",
    val CompanyBusinessTypes: String = "",
    val Logo: String = "",

    val Picture: String = "",
    val DefaultPicture: String = "",
    val BrandData: ProductBrandDTO? = null,

    val Category: String = "",
    val Breadcrumbs: List<BreadcrumbModel> = emptyList(),

    val MinimumOrderUnit: String = "",
    val MaksimumOrderUnit: String = "",
    val Customization: String = "",
    val LeadTime: String = "",
    val DimensionsPerUnitType: String = "",
    val WeightPerUnitType: String = "",
    val Origin: String = "",

    val Rating: Double = 0.0,
    val ColorId: Int = 0,
    val StoreId: Int = 0,
    val Price: Double = 0.0,
    val ReviewNumber: Int? = null,

    val AdvertSponsoreds: List<AdvertSponsoredDTO> = emptyList(),

    val Prices: List<WholesaleProductPriceDTO> = emptyList(),
    val MainPrice: WholesaleProductPriceDTO? = null,

    val VerificationSummary: CompanyVerificationSummaryDTO? = null,
    val PaymentTerms: List<CompanyPaymentTermDTO> = emptyList(),
    val PaymentTermsFormatted: String = ""
)