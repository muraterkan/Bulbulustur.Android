package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.B2CProductFilterDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductVariantPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantPictureRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
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

    val ErrorMessage: String? = null
) {

    val ProductListData: B2CProductDataDTO?
        get() = ProductListResult?.Data

    val StoreProductListData: B2CProductDataDTO?
        get() = StoreProductListResult?.Data

    val ProductVariantPictures: List<ProductVariantPictureDTO>
        get() = ProductVariantPicturesResult?.Data ?: emptyList()

    val ProductVariants: List<ProductVariantDTO>
        get() = ProductVariantsResult?.Data ?: emptyList()

    val ColorVariants: List<ProductVariantDTO>
        get() = ColorVariantsResult?.Data ?: emptyList()

    val SizeVariants: List<ProductVariantDTO>
        get() = SizeVariantsResult?.Data ?: emptyList()

    val OtherStorePrices: List<ProductVariantDTO>
        get() = OtherStorePricesResult?.Data ?: emptyList()

    val HasNextProductPage: Boolean
        get() = ProductListData?.Products2?.HasNextPage ?: false

    val HasNextStoreProductPage: Boolean
        get() = StoreProductListData?.Products2?.HasNextPage ?: false
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

    data object ClearProductDetail : ProductControllerEvent

    data object ClearError : ProductControllerEvent
}

class ProductController(
    private val executeService: IExecuteService,
    private val productRepository: IProductRepository,
    private val productVariantRepository: IProductVariantRepository,
    private val productVariantPictureRepository: IProductVariantPictureRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(ProductControllerState())

    val State: StateFlow<ProductControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: ProductControllerEvent
    ) {
        when (event) {
            is ProductControllerEvent.LoadProducts -> {
                List(
                    filters = event.Filters,
                    page = event.Page,
                    pageSize = event.PageSize
                )
            }

            is ProductControllerEvent.LoadProduct -> {
                GetById(
                    productId = event.ProductId
                )
            }

            is ProductControllerEvent.LoadProductDetail -> {
                Detail(
                    languageId = event.LanguageId,
                    storeId = event.StoreId,
                    productId = event.ProductId,
                    variantId = event.VariantId
                )
            }

            is ProductControllerEvent.LoadProductVariantPictures -> {
                VariantPictures(
                    variantId = event.VariantId,
                    count = event.Count
                )
            }

            is ProductControllerEvent.LoadStoreProducts -> {
                StoreProductList(
                    storeId = event.StoreId,
                    filters = event.Filters,
                    page = event.Page,
                    pageSize = event.PageSize
                )
            }

            is ProductControllerEvent.LoadOtherStorePrices -> {
                OtherSellerList(
                    languageId = event.LanguageId,
                    productId = event.ProductId,
                    variantId = event.VariantId,
                    storeId = event.StoreId
                )
            }

            is ProductControllerEvent.LoadProductVariants -> {
                Variants(
                    languageId = event.LanguageId,
                    productId = event.ProductId,
                    storeId = event.StoreId,
                    count = event.Count
                )
            }

            is ProductControllerEvent.LoadSelectedVariant -> {
                SelectedVariant(
                    languageId = event.LanguageId,
                    variantId = event.VariantId
                )
            }

            is ProductControllerEvent.LoadSmallestPrice -> {
                SmallestPrice(
                    languageId = event.LanguageId,
                    productId = event.ProductId
                )
            }

            is ProductControllerEvent.LoadColorVariants -> {
                ColorVariants(
                    languageId = event.LanguageId,
                    productId = event.ProductId,
                    variantId = event.VariantId
                )
            }

            is ProductControllerEvent.LoadSizeVariants -> {
                SizeVariants(
                    languageId = event.LanguageId,
                    productId = event.ProductId,
                    variantId = event.VariantId
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
            filters = filters,
            page = page,
            pageSize = pageSize
        )
    }

    fun List(
        filters: B2CProductFilterDTO,
        page: Int = 1,
        pageSize: Int = 50
    ) {
        viewModelScope.launch {
            StartLoading(
                actionName = "List"
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
                        filters = filters,
                        page = page,
                        pageSize = pageSize
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "List",
                    ProductListResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                actionName = "GetById"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.Product.GetProductByIdAsync." +
                                "productId=$productId"
                ) {
                    productRepository.GetProductByIdAsync(
                        productId = productId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "GetById",
                    ProductResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    ProductVariantsResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "Variants"
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
                        languageId = languageId,
                        productId = productId,
                        storeId = storeId,
                        count = count
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Variants",
                    ProductVariantsResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    SelectedVariantResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "SelectedVariant"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetProductVariantByIdExtendedAsync." +
                                "languageId=$languageId." +
                                "variantId=$variantId"
                ) {
                    productVariantRepository.GetProductVariantByIdExtendedAsync(
                        languageId = languageId,
                        variantId = variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "SelectedVariant",
                    SelectedVariantResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    SmallestPriceResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "SmallestPrice"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductVariant.GetSmallestPriceWithStoreInfoAsync." +
                                "languageId=$languageId." +
                                "productId=$productId"
                ) {
                    productVariantRepository.GetSmallestPriceWithStoreInfoAsync(
                        languageId = languageId,
                        productId = productId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "SmallestPrice",
                    SmallestPriceResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    ColorVariantsResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "ColorVariants"
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
                        languageId = languageId,
                        productId = productId,
                        variantId = variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "ColorVariants",
                    ColorVariantsResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    SizeVariantsResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "SizeVariants"
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
                        languageId = languageId,
                        productId = productId,
                        variantId = variantId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "SizeVariants",
                    SizeVariantsResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    ProductVariantPicturesResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "VariantPictures"
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
                            variantId = variantId,
                            count = count
                        )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "VariantPictures",
                    ProductVariantPicturesResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                actionName = "StoreProductList"
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
                        storeId = storeId,
                        filters = filters,
                        page = page,
                        pageSize = pageSize
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "StoreProductList",
                    StoreProductListResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
                    OtherStorePricesResult = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName = "OtherSellerList"
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
                        languageId = languageId,
                        productId = productId,
                        variantId = variantId,
                        storeId = storeId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "OtherSellerList",
                    OtherStorePricesResult = response,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
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
            filters = filters,
            page = page,
            pageSize = pageSize
        )
    }

    fun ClearProductDetail() {
        _state.update {
            it.copy(
                ProductDetailResult = null,
                ProductVariantPicturesResult = null,
                ProductVariantsResult = null,
                ColorVariantsResult = null,
                SizeVariantsResult = null,
                SelectedVariantResult = null,
                SmallestPriceResult = null,
                OtherStorePricesResult = null,
                ErrorMessage = null
            )
        }
    }

    fun ClearError() {
        _state.update {
            it.copy(
                ErrorMessage = null
            )
        }
    }

    private fun StartLoading(
        actionName: String
    ) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = actionName,
                ErrorMessage = null
            )
        }
    }
}