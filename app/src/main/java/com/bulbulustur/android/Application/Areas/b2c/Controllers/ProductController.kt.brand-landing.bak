package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.AdvertSponsoredDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrandSectionDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductBrowsingHistoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAdvertSponsoredRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrandSectionRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductComplaintRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductLowPriceReportRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPictureRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductBrowsingHistoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductComplaintInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductLowPriceReportInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,

    val ProductListResult: Result<B2CProductDataDTO>? = null,
    val ProductResult: Result<ProductUpdateModel?>? = null,
    val ProductDetailResult: Result<ProductDTO?>? = null,
    val ProductVariantPicturesResult: Result<List<ProductVariantPictureDTO>>? = null,
    val ProductVariantsResult: Result<List<ProductVariantDTO>>? = null,
    val ColorVariantsResult: Result<List<ProductVariantDTO>>? = null,
    val SizeVariantsResult: Result<List<ProductVariantDTO>>? = null,
    val SelectedVariantResult: Result<ProductVariantDTO?>? = null,
    val SmallestPriceResult: Result<ProductVariantDTO?>? = null,
    val StoreProductListResult: Result<B2CProductDataDTO>? = null,
    val OtherStorePricesResult: Result<List<ProductVariantDTO>>? = null,

    val SponsoredAdvertsResult: Result<List<AdvertSponsoredDTO>>? = null,
    val CategorySponsoredAdvertsResult: Result<List<AdvertSponsoredDTO>>? = null,
    val ProductBrandSectionsResult: Result<List<ProductBrandSectionDTO>>? = null,
    val ProductBrowsingHistoryResult:
    Result<PaginatedList<ProductBrowsingHistoryDTO>>? = null,
    val InsertProductBrowsingHistoryResult: Result<Unit>? = null,
    val ProductComplaintInsertResult: Result<Unit>? = null,
    val ProductLowPriceReportInsertResult: Result<Unit>? = null,
    val RelatedCategoriesResult: Result<List<ProductCategoryDTO>>? = null,

    val ErrorMessage: String? = null
) {

    val ProductListData: B2CProductDataDTO?
        get() =
            ProductListResult
                ?.Data

    val StoreProductListData: B2CProductDataDTO?
        get() =
            StoreProductListResult
                ?.Data

    val ProductVariantPictures: List<ProductVariantPictureDTO>
        get() =
            ProductVariantPicturesResult
                ?.Data
                .orEmpty()

    val ProductVariants: List<ProductVariantDTO>
        get() =
            ProductVariantsResult
                ?.Data
                .orEmpty()

    val ColorVariants: List<ProductVariantDTO>
        get() =
            ColorVariantsResult
                ?.Data
                .orEmpty()

    val SizeVariants: List<ProductVariantDTO>
        get() =
            SizeVariantsResult
                ?.Data
                .orEmpty()

    val OtherStorePrices: List<ProductVariantDTO>
        get() =
            OtherStorePricesResult
                ?.Data
                .orEmpty()

    val SponsoredAdverts: List<AdvertSponsoredDTO>
        get() =
            SponsoredAdvertsResult
                ?.Data
                .orEmpty()

    val CategorySponsoredAdverts: List<AdvertSponsoredDTO>
        get() = CategorySponsoredAdvertsResult?.Data.orEmpty()

    val ProductBrandSections: List<ProductBrandSectionDTO>
        get() =
            ProductBrandSectionsResult
                ?.Data
                .orEmpty()

    val ProductBrowsingHistories: List<ProductBrowsingHistoryDTO>
        get() =
            ProductBrowsingHistoryResult
                ?.Data
                ?.Items
                .orEmpty()

    val RelatedCategories: List<ProductCategoryDTO>
        get() =
            RelatedCategoriesResult
                ?.Data
                .orEmpty()

    val HasNextProductPage: Boolean
        get() =
            ProductListData
                ?.Products2
                ?.HasNextPage
                ?: false

    val HasNextStoreProductPage: Boolean
        get() =
            StoreProductListData
                ?.Products2
                ?.HasNextPage
                ?: false

    val HasNextBrowsingHistoryPage: Boolean
        get() =
            ProductBrowsingHistoryResult
                ?.Data
                ?.HasNextPage
                ?: false
}

sealed interface ProductControllerEvent {

    data class LoadProducts(
        val Filters: B2CProductFilterDTO,
        val Page: Int = 1,
        val PageSize: Int = 50
    ) : ProductControllerEvent

    data class LoadProduct(
        val ProductId: Int
    ) : ProductControllerEvent

    data class LoadProductDetail(
        val LanguageId: Int,
        val StoreId: Int,
        val ProductId: Int,
        val VariantId: Int = 0
    ) : ProductControllerEvent

    data class LoadProductVariantPictures(
        val VariantId: Int,
        val Count: Int = 10
    ) : ProductControllerEvent

    data class LoadStoreProducts(
        val StoreId: Int,
        val Filters: B2CProductFilterDTO,
        val Page: Int = 1,
        val PageSize: Int = 50
    ) : ProductControllerEvent

    data class LoadOtherStorePrices(
        val LanguageId: Int,
        val ProductId: Int,
        val VariantId: Int,
        val StoreId: Int
    ) : ProductControllerEvent

    data class LoadProductVariants(
        val LanguageId: Int,
        val ProductId: Int,
        val StoreId: Int,
        val Count: Int = 100
    ) : ProductControllerEvent

    data class LoadSelectedVariant(
        val LanguageId: Int,
        val VariantId: Int
    ) : ProductControllerEvent

    data class LoadSmallestPrice(
        val LanguageId: Int,
        val ProductId: Int
    ) : ProductControllerEvent

    data class LoadColorVariants(
        val LanguageId: Int,
        val ProductId: Int,
        val VariantId: Int
    ) : ProductControllerEvent

    data class LoadSizeVariants(
        val LanguageId: Int,
        val ProductId: Int,
        val VariantId: Int
    ) : ProductControllerEvent

    data class LoadSponsoredAdverts(
        val LanguageId: Int,
        val ProductCategoryId: Int,
        val Count: Int = 8
    ) : ProductControllerEvent

    data class LoadProductBrandSections(
        val LanguageId: Int,
        val BrandId: Int,
        val Count: Int = 5
    ) : ProductControllerEvent

    data class LoadProductBrowsingHistories(
        val MemberId: Int,
        val Page: Int = 1,
        val PageSize: Int = 20
    ) : ProductControllerEvent

    data class InsertProductBrowsingHistory(
        val MemberId: Int,
        val StoreId: Int,
        val ProductId: Int,
        val VariantId: Int
    ) : ProductControllerEvent

    data class LoadRelatedCategories(
        val LanguageId: Int,
        val ProductCategoryId: Int
    ) : ProductControllerEvent

    data object ClearProductDetail : ProductControllerEvent

    data object ClearError : ProductControllerEvent
}

class ProductController(
    private val executeService: IExecuteService,
    private val productRepository: IProductRepository,
    private val productVariantRepository: IProductVariantRepository,
    private val productVariantPictureRepository: IProductVariantPictureRepository,
    private val advertSponsoredRepository: IAdvertSponsoredRepository,
    private val productBrandSectionRepository: IProductBrandSectionRepository,
    private val productBrowsingHistoryRepository: IProductBrowsingHistoryRepository,
    private val productCategoryRepository: IProductCategoryRepository
    ,
    private val productComplaintRepository: IProductComplaintRepository,
    private val productLowPriceReportRepository: IProductLowPriceReportRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            ProductControllerState()
        )

    val State: StateFlow<ProductControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: ProductControllerEvent
    ) {
        when (event) {
            is ProductControllerEvent.LoadProducts -> {
                List(
                    filters =
                        event.Filters,
                    page =
                        event.Page,
                    pageSize =
                        event.PageSize
                )
            }

            is ProductControllerEvent.LoadProduct -> {
                GetById(
                    productId =
                        event.ProductId
                )
            }

            is ProductControllerEvent.LoadProductDetail -> {
                Detail(
                    languageId =
                        event.LanguageId,
                    storeId =
                        event.StoreId,
                    productId =
                        event.ProductId,
                    variantId =
                        event.VariantId
                )
            }

            is ProductControllerEvent.LoadProductVariantPictures -> {
                VariantPictures(
                    variantId =
                        event.VariantId,
                    count =
                        event.Count
                )
            }

            is ProductControllerEvent.LoadStoreProducts -> {
                StoreProductList(
                    storeId =
                        event.StoreId,
                    filters =
                        event.Filters,
                    page =
                        event.Page,
                    pageSize =
                        event.PageSize
                )
            }

            is ProductControllerEvent.LoadOtherStorePrices -> {
                OtherSellerList(
                    languageId =
                        event.LanguageId,
                    productId =
                        event.ProductId,
                    variantId =
                        event.VariantId,
                    storeId =
                        event.StoreId
                )
            }

            is ProductControllerEvent.LoadProductVariants -> {
                Variants(
                    languageId =
                        event.LanguageId,
                    productId =
                        event.ProductId,
                    storeId =
                        event.StoreId,
                    count =
                        event.Count
                )
            }

            is ProductControllerEvent.LoadSelectedVariant -> {
                SelectedVariant(
                    languageId =
                        event.LanguageId,
                    variantId =
                        event.VariantId
                )
            }

            is ProductControllerEvent.LoadSmallestPrice -> {
                SmallestPrice(
                    languageId =
                        event.LanguageId,
                    productId =
                        event.ProductId
                )
            }

            is ProductControllerEvent.LoadColorVariants -> {
                ColorVariants(
                    languageId =
                        event.LanguageId,
                    productId =
                        event.ProductId,
                    variantId =
                        event.VariantId
                )
            }

            is ProductControllerEvent.LoadSizeVariants -> {
                SizeVariants(
                    languageId =
                        event.LanguageId,
                    productId =
                        event.ProductId,
                    variantId =
                        event.VariantId
                )
            }

            is ProductControllerEvent.LoadSponsoredAdverts -> {
                SponsoredAdverts(
                    languageId =
                        event.LanguageId,
                    productCategoryId =
                        event.ProductCategoryId,
                    count =
                        event.Count
                )
            }

            is ProductControllerEvent.LoadProductBrandSections -> {
                ProductBrandSections(
                    languageId =
                        event.LanguageId,
                    brandId =
                        event.BrandId,
                    count =
                        event.Count
                )
            }

            is ProductControllerEvent.LoadProductBrowsingHistories -> {
                ProductBrowsingHistories(
                    memberId =
                        event.MemberId,
                    page =
                        event.Page,
                    pageSize =
                        event.PageSize
                )
            }

            is ProductControllerEvent.InsertProductBrowsingHistory -> {
                InsertBrowsingHistory(
                    memberId =
                        event.MemberId,
                    storeId =
                        event.StoreId,
                    productId =
                        event.ProductId,
                    variantId =
                        event.VariantId
                )
            }

            is ProductControllerEvent.LoadRelatedCategories -> {
                RelatedCategories(
                    languageId =
                        event.LanguageId,
                    productCategoryId =
                        event.ProductCategoryId
                )
            }

            ProductControllerEvent.ClearProductDetail -> {
                ClearProductDetail()
            }

            ProductControllerEvent.ClearError -> {
                ClearError()
            }
        }
    }

    fun Index(
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ) {
        List(
            filters =
                filters,
            page =
                page,
            pageSize =
                pageSize
        )
    }

    fun List(
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ) {
        viewModelScope.launch {
            StartLoading(
                actionName =
                    "List"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductDataAsync." +
                                "page=$page." +
                                "pageSize=$pageSize." +
                                filters.toString()
                ) {
                    productRepository.GetProductDataAsync(
                        filters =
                            filters,
                        page =
                            page,
                        pageSize =
                            pageSize
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "List",
                    ProductListResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun GetById(
        productId: Int
    ) {
        viewModelScope.launch {
            StartLoading(
                actionName =
                    "GetById"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductByIdAsync." +
                                "productId=$productId"
                ) {
                    productRepository.GetProductByIdAsync(
                        productId =
                            productId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetById",
                    ProductResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun Detail(
        languageId: Int,
        storeId: Int,
        productId: Int,
        variantId: Int = 0
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    ProductDetailResult = null
                )
            }

            StartLoading(
                actionName = "Detail"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductByIdExtendedAsync." +
                                "languageId=$languageId." +
                                "storeId=$storeId." +
                                "productId=$productId." +
                                "variantId=$variantId"
                ) {
                    productRepository.GetProductByIdExtendedAsync(
                        languageId = languageId,
                        storeId = storeId,
                        productId = productId,
                        variantId = variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Detail",
                    ProductDetailResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun Variants(
        languageId: Int,
        productId: Int,
        storeId: Int,
        count: Int = 100
    ) {
        if (
            languageId <= 0 ||
            productId <= 0 ||
            storeId <= 0
        ) {
            _state.update {
                it.copy(
                    ProductVariantsResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "Variants"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetProductVariantsAsync." +
                                "languageId=$languageId." +
                                "productId=$productId." +
                                "storeId=$storeId." +
                                "count=$count"
                ) {
                    productVariantRepository.GetProductVariantsAsync(
                        languageId =
                            languageId,
                        productId =
                            productId,
                        storeId =
                            storeId,
                        count =
                            count
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "Variants",
                    ProductVariantsResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun SelectedVariant(
        languageId: Int,
        variantId: Int
    ) {
        if (
            languageId <= 0 ||
            variantId <= 0
        ) {
            _state.update {
                it.copy(
                    SelectedVariantResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "SelectedVariant"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetProductVariantByIdExtendedAsync." +
                                "languageId=$languageId." +
                                "variantId=$variantId"
                ) {
                    productVariantRepository.GetProductVariantByIdExtendedAsync(
                        languageId =
                            languageId,
                        variantId =
                            variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SelectedVariant",
                    SelectedVariantResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun SmallestPrice(
        languageId: Int,
        productId: Int
    ) {
        if (
            languageId <= 0 ||
            productId <= 0
        ) {
            _state.update {
                it.copy(
                    SmallestPriceResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "SmallestPrice"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetSmallestPriceWithStoreInfoAsync." +
                                "languageId=$languageId." +
                                "productId=$productId"
                ) {
                    productVariantRepository.GetSmallestPriceWithStoreInfoAsync(
                        languageId =
                            languageId,
                        productId =
                            productId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SmallestPrice",
                    SmallestPriceResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun ColorVariants(
        languageId: Int,
        productId: Int,
        variantId: Int
    ) {
        if (
            languageId <= 0 ||
            productId <= 0 ||
            variantId <= 0
        ) {
            _state.update {
                it.copy(
                    ColorVariantsResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "ColorVariants"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetProductColorVariantsAsync." +
                                "languageId=$languageId." +
                                "productId=$productId." +
                                "variantId=$variantId"
                ) {
                    productVariantRepository.GetProductColorVariantsAsync(
                        languageId =
                            languageId,
                        productId =
                            productId,
                        variantId =
                            variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ColorVariants",
                    ColorVariantsResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun SizeVariants(
        languageId: Int,
        productId: Int,
        variantId: Int
    ) {
        if (
            languageId <= 0 ||
            productId <= 0 ||
            variantId <= 0
        ) {
            _state.update {
                it.copy(
                    SizeVariantsResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "SizeVariants"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetProductSizeVariantsAsync." +
                                "languageId=$languageId." +
                                "productId=$productId." +
                                "variantId=$variantId"
                ) {
                    productVariantRepository.GetProductSizeVariantsAsync(
                        languageId =
                            languageId,
                        productId =
                            productId,
                        variantId =
                            variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SizeVariants",
                    SizeVariantsResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun VariantPictures(
        variantId: Int,
        count: Int = 10
    ) {
        if (variantId <= 0) {
            _state.update {
                it.copy(
                    ProductVariantPicturesResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "VariantPictures"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariantPicture." +
                                "GetProductVariantPicturesAsync." +
                                "variantId=$variantId." +
                                "count=$count"
                ) {
                    productVariantPictureRepository
                        .GetProductVariantPicturesAsync(
                            variantId =
                                variantId,
                            count =
                                count
                        )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "VariantPictures",
                    ProductVariantPicturesResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun StoreProductList(
        storeId: Int,
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ) {
        viewModelScope.launch {
            StartLoading(
                actionName =
                    "StoreProductList"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetStoreProductDataAsync." +
                                "storeId=$storeId." +
                                "page=$page." +
                                "pageSize=$pageSize." +
                                filters.toString()
                ) {
                    productRepository.GetStoreProductDataAsync(
                        storeId =
                            storeId,
                        filters =
                            filters,
                        page =
                            page,
                        pageSize =
                            pageSize
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "StoreProductList",
                    StoreProductListResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun OtherSellerList(
        languageId: Int,
        productId: Int,
        variantId: Int,
        storeId: Int
    ) {
        if (
            languageId <= 0 ||
            productId <= 0 ||
            variantId <= 0 ||
            storeId <= 0
        ) {
            _state.update {
                it.copy(
                    OtherStorePricesResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "OtherSellerList"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetOtherStorePriceAsync." +
                                "languageId=$languageId." +
                                "productId=$productId." +
                                "variantId=$variantId." +
                                "storeId=$storeId"
                ) {
                    productVariantRepository.GetOtherStorePriceAsync(
                        languageId =
                            languageId,
                        productId =
                            productId,
                        variantId =
                            variantId,
                        storeId =
                            storeId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "OtherSellerList",
                    OtherStorePricesResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun SponsoredAdverts(
        languageId: Int,
        productCategoryId: Int,
        count: Int = 8
    ) {
        if (
            languageId <= 0 ||
            productCategoryId <= 0
        ) {
            _state.update {
                it.copy(
                    SponsoredAdvertsResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "SponsoredAdverts"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetSponsoredAdvertsAsync." +
                                "languageId=$languageId." +
                                "productCategoryId=$productCategoryId." +
                                "count=$count"
                ) {
                    advertSponsoredRepository.GetSponsoredAdvertsAsync(
                        languageId =
                            languageId,
                        productCategoryId =
                            productCategoryId,
                        count =
                            count
                    )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SponsoredAdverts",
                    SponsoredAdvertsResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun SponsoredAdvertsByProductCategoryList(
        languageId: Int,
        productCategoryIds: List<Int>,
        count: Int = 8
    ) {
        val categoryIds = productCategoryIds.filter { it > 0 }.distinct()

        if (languageId <= 0 || categoryIds.isEmpty()) {
            _state.update {
                it.copy(
                    CategorySponsoredAdvertsResult = null
                )
            }
            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "CategorySponsoredAdverts"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetSponsoredAdvertsByProductCategoryListAsync." +
                                "languageId=$languageId." +
                                "categoryIds=${categoryIds.joinToString(",")}." +
                                "count=$count"
                ) {
                    advertSponsoredRepository.GetSponsoredAdvertsByProductCategoryListAsync(
                        languageId = languageId,
                        productCategoryIds = categoryIds,
                        count = count
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "CategorySponsoredAdverts",
                    CategorySponsoredAdvertsResult = response,
                    ErrorMessage = response.Message.takeIf {
                        !response.Success
                    }
                )
            }
        }
    }

    fun ProductBrandSections(
        languageId: Int,
        brandId: Int,
        count: Int = 5
    ) {
        if (
            languageId <= 0 ||
            brandId <= 0
        ) {
            _state.update {
                it.copy(
                    ProductBrandSectionsResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "ProductBrandSections"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductBrandSectionsAsync." +
                                "languageId=$languageId." +
                                "brandId=$brandId." +
                                "count=$count"
                ) {
                    productBrandSectionRepository
                        .GetProductBrandSectionsAsync(
                            languageId =
                                languageId,
                            brandId =
                                brandId,
                            count =
                                count
                        )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ProductBrandSections",
                    ProductBrandSectionsResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun ProductBrowsingHistories(
        memberId: Int,
        page: Int = 1,
        pageSize: Int = 20
    ) {
        if (memberId <= 0) {
            _state.update {
                it.copy(
                    ProductBrowsingHistoryResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "ProductBrowsingHistories"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        ""
                ) {
                    productBrowsingHistoryRepository
                        .GetProductBrowsingHistoriesAsync(
                            memberId =
                                memberId,
                            page =
                                page,
                            pageSize =
                                pageSize
                        )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ProductBrowsingHistories",
                    ProductBrowsingHistoryResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun InsertBrowsingHistory(
        memberId: Int,
        storeId: Int,
        productId: Int,
        variantId: Int
    ) {
        if (
            memberId <= 0 ||
            storeId <= 0 ||
            productId <= 0 ||
            variantId <= 0
        ) {
            return
        }

        viewModelScope.launch {
            val response =
                executeService.PostAsync(
                    operationType =
                        "b2c.ProductBrowsingHistory.InsertProductBrowsingHistoryAsync"
                ) {
                    productBrowsingHistoryRepository
                        .InsertProductBrowsingHistoryAsync(
                            memberId =
                                memberId,
                            model =
                                ProductBrowsingHistoryInsertModel(
                                    InsertedBy =
                                        memberId,
                                    MemberId =
                                        memberId,
                                    StoreId =
                                        storeId,
                                    ProductId =
                                        productId,
                                    VariantId =
                                        variantId
                                )
                        )
                }

            _state.update {
                it.copy(
                    CurrentAction =
                        "InsertProductBrowsingHistory",
                    InsertProductBrowsingHistoryResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun RelatedCategories(
        languageId: Int,
        productCategoryId: Int
    ) {
        if (
            languageId <= 0 ||
            productCategoryId <= 0
        ) {
            _state.update {
                it.copy(
                    RelatedCategoriesResult =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "RelatedCategories"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductChildCategoriesAsync." +
                                "languageId=$languageId." +
                                "productCategoryId=$productCategoryId"
                ) {
                    productCategoryRepository
                        .GetProductChildCategoriesAsync(
                            languageId =
                                languageId,
                            productCategoryId =
                                productCategoryId
                        )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "RelatedCategories",
                    RelatedCategoriesResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun RefreshProductList(
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ) {
        List(
            filters =
                filters,
            page =
                page,
            pageSize =
                pageSize
        )
    }

    fun ClearProductDetail() {
        _state.update {
            it.copy(
                ProductDetailResult =
                    null,
                ProductVariantPicturesResult =
                    null,
                ProductVariantsResult =
                    null,
                ColorVariantsResult =
                    null,
                SizeVariantsResult =
                    null,
                SelectedVariantResult =
                    null,
                SmallestPriceResult =
                    null,
                OtherStorePricesResult =
                    null,
                SponsoredAdvertsResult =
                    null,
                ProductBrandSectionsResult =
                    null,
                ProductBrowsingHistoryResult =
                    null,
                InsertProductBrowsingHistoryResult =
                    null,
                RelatedCategoriesResult =
                    null,
                ErrorMessage =
                    null
            )
        }
    }

    fun ClearError() {
        _state.update {
            it.copy(
                ErrorMessage =
                    null
            )
        }
    }

    private fun StartLoading(
        actionName: String
    ) {
        _state.update {
            it.copy(
                IsLoading =
                    true,
                CurrentAction =
                    actionName,
                ErrorMessage =
                    null
            )
        }
    }
}
