package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.B2BProductDataDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductRelatedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRepository
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
    val ProductListResult: Result<B2BProductDataDTO>? = null,
    val ProductDetailResult: Result<WholesaleProductDTO?>? = null,
    val RelatedProductsResult: Result<List<WholesaleProductRelatedDTO>>? = null,
    val RelatedCategoriesResult: Result<List<ProductCategoryDTO>>? = null,
    val ErrorMessage: String? = null,
    val RelatedProductsErrorMessage: String? = null,
    val RelatedCategoriesErrorMessage: String? = null
) {
    val ProductListData: B2BProductDataDTO?
        get() = ProductListResult?.Data

    val RelatedCategories: List<ProductCategoryDTO>
        get() = RelatedCategoriesResult?.Data.orEmpty()
}

class ProductController(
    private val executeService: IExecuteService,
    private val wholesaleProductRepository: IWholesaleProductRepository,
    private val productCategoryRepository: IProductCategoryRepository
) : BaseController() {

    private val _state = MutableStateFlow(ProductControllerState())
    val State: StateFlow<ProductControllerState> = _state.asStateFlow()

    fun List(languageId: Int, productCategoryId: Int = 0, page: Int = 1, pageSize: Int = 50, sortOrder: String = "Name_Desc", brandIds: String = "") {
        if (languageId <= 0) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "List",
                    ProductListResult = null,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading("List")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.GetProductDataAsync.languageId=$languageId.productCategoryId=$productCategoryId.page=$page.pageSize=$pageSize.sortOrder=$sortOrder.brandIds=$brandIds"
            ) {
                wholesaleProductRepository.GetProductDataAsync(
                    languageId = languageId,
                    productCategoryId = productCategoryId,
                    page = page,
                    pageSize = pageSize,
                    sortOrder = sortOrder,
                    brandIds = brandIds
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "List",
                    ProductListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun Detail(languageId: Int, wholesaleProductId: Int) {
        if (languageId <= 0 || wholesaleProductId <= 0) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Detail",
                    ProductListResult = null,
                    ProductDetailResult = null,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading("Detail")

            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.GetProductByIdExtendedAsync.languageId=$languageId.wholesaleProductId=$wholesaleProductId"
            ) {
                wholesaleProductRepository.GetProductByIdExtendedAsync(
                    languageId = languageId,
                    wholesaleProductId = wholesaleProductId
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "Detail",
                    ProductDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetProductRelatedsAsync(languageId: Int, wholesaleProductId: Int, count: Int = 10) {
        if (languageId <= 0 || wholesaleProductId <= 0 || count <= 0) {
            _state.update {
                it.copy(
                    RelatedProductsResult = null,
                    RelatedProductsErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.GetProductRelatedsAsync.languageId=$languageId.wholesaleProductId=$wholesaleProductId.count=$count"
            ) {
                wholesaleProductRepository.GetProductRelatedsAsync(
                    languageId = languageId,
                    wholesaleProductId = wholesaleProductId,
                    count = count
                )
            }

            _state.update {
                it.copy(
                    RelatedProductsResult = response,
                    RelatedProductsErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun RelatedCategories(languageId: Int, productCategoryId: Int) {
        if (languageId <= 0 || productCategoryId <= 0) {
            _state.update {
                it.copy(
                    RelatedCategoriesResult = null,
                    RelatedCategoriesErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            val response = executeService.GetAsync(
                cacheKey = "b2b.Product.GetProductChildCategoriesAsync.languageId=$languageId.productCategoryId=$productCategoryId"
            ) {
                productCategoryRepository.GetProductChildCategoriesAsync(
                    languageId = languageId,
                    productCategoryId = productCategoryId
                )
            }

            _state.update {
                it.copy(
                    RelatedCategoriesResult = response,
                    RelatedCategoriesErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun ClearProductDetail() {
        _state.update {
            it.copy(
                IsLoading = false,
                CurrentAction = null,
                ProductListResult = null,
                ProductDetailResult = null,
                RelatedProductsResult = null,
                RelatedCategoriesResult = null,
                ErrorMessage = null,
                RelatedProductsErrorMessage = null,
                RelatedCategoriesErrorMessage = null
            )
        }
    }

    fun ClearError() {
        _state.update {
            it.copy(
                ErrorMessage = null,
                RelatedProductsErrorMessage = null,
                RelatedCategoriesErrorMessage = null
            )
        }
    }

    private fun StartLoading(actionName: String) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = actionName,
                ErrorMessage = null
            )
        }
    }
}