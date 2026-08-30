package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Datastore.ProductCategoryDataStore
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCategoryDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySliderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleHomepageSpecialContentRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategorySliderRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategorySupplierRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoryControllerState(
    val IsLoading: Boolean = false,
    val IsCategoryContentsLoading: Boolean = false,
    val IsSpecialContentsLoading: Boolean = false,
    val IsCategoryContentListLoading: Boolean = false,
    val IsCategorySliderLoading: Boolean = false,
    val IsCategorySuppliersLoading: Boolean = false,

    val CurrentAction: String? = null,
    val ErrorMessage: String? = null,
    val CategoryContentsErrorMessage: String? = null,
    val SpecialContentsErrorMessage: String? = null,
    val CategoryContentListErrorMessage: String? = null,
    val CategorySliderErrorMessage: String? = null,
    val CategorySuppliersErrorMessage: String? = null,

    val CategoryResult: Result<ProductCategoryDTO?>? = null,
    val CategoryListResult: Result<List<ProductCategoryDTO>>? = null,
    val CachedCategories: List<ProductCategoryDTO> = emptyList(),
    val ChildCategoryListResult: Result<List<ProductCategoryDTO>>? = null,

    val WholesaleProductCategoryContentsResult: Result<WholesaleProductCategoryContentDTO>? = null,
    val WholesaleProductCategoryContentListResult: Result<PaginatedList<WholesaleProductCategoryContentDTO>>? = null,
    val SpecialContentsResult: Result<List<WholesaleHomepageSpecialContentDTO>>? = null,
    val CategorySliderResult: Result<WholesaleProductCategorySliderDTO?>? = null,
    val CategorySuppliersResult: Result<List<WholesaleProductCategorySupplierDTO>>? = null
) {
    val Category: ProductCategoryDTO?
        get() = CategoryResult?.Data

    val Categories: List<ProductCategoryDTO>
        get() = CategoryListResult?.Data
            ?.takeIf { it.isNotEmpty() }
            ?: CachedCategories

    val ChildCategories: List<ProductCategoryDTO>
        get() = ChildCategoryListResult?.Data.orEmpty()

    val CategoryContents
        get() = WholesaleProductCategoryContentsResult
            ?.Data
            ?.Groups
            .orEmpty()

    val SpecialContents: List<WholesaleHomepageSpecialContentDTO>
        get() = SpecialContentsResult?.Data.orEmpty()

    val CategorySlider: WholesaleProductCategorySliderDTO?
        get() = CategorySliderResult?.Data

    val CategorySuppliers: List<WholesaleProductCategorySupplierDTO>
        get() = CategorySuppliersResult?.Data.orEmpty()
}

class CategoryController(
    private val executeService: IExecuteService,
    private val productCategoryRepository: IProductCategoryRepository,
    private val productCategoryDataStore: ProductCategoryDataStore,
    private val wholesaleProductCategoryContentRepository: IWholesaleProductCategoryContentRepository,
    private val wholesaleHomepageSpecialContentRepository: IWholesaleHomepageSpecialContentRepository,
    private val wholesaleProductCategorySliderRepository: IWholesaleProductCategorySliderRepository,
    private val wholesaleProductCategorySupplierRepository: IWholesaleProductCategorySupplierRepository
) : BaseController() {

    private val _state = MutableStateFlow(CategoryControllerState())

    val State: StateFlow<CategoryControllerState> =
        _state.asStateFlow()

    fun LoadHome(
        languageId: Int,
        count: Int = 30000
    ) {
        if (languageId <= 0) {
            SetError(
                BBLocalization.Current.Get(
                    key = "a2538f8a-25cd-4e64-8572-75585c749dc0",
                    fallback = "Dil bilgisi bulunamadı."
                )
            )

            return
        }

        viewModelScope.launch {
            val cachedCategories =
                productCategoryDataStore.Get(languageId)

            if (cachedCategories.isNotEmpty()) {
                _state.update {
                    it.copy(
                        IsLoading = false,
                        CurrentAction = null,
                        CachedCategories = cachedCategories,
                        ErrorMessage = null
                    )
                }
            } else {
                Start("LoadHome")
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.LoadHome.languageId=$languageId.count=$count"
                ) {
                    productCategoryRepository.GetCachedProductCategoriesAsync(
                        languageId = languageId
                    )
                }

            if (
                response.Success &&
                response.Data.orEmpty().isNotEmpty()
            ) {
                productCategoryDataStore.Set(
                    languageId,
                    response.Data.orEmpty()
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    CategoryListResult = response,
                    CachedCategories = response.Data
                        ?.takeIf { categories -> categories.isNotEmpty() }
                        ?: it.CachedCategories,
                    ErrorMessage = response
                        .takeIf { result -> !result.Success }
                        ?.Message
                )
            }
        }
    }

    fun LoadDetail(
        languageId: Int,
        productCategoryId: Int
    ) {
        if (languageId <= 0) {
            SetError(
                BBLocalization.Current.Get(
                    key = "a2538f8a-25cd-4e64-8572-75585c749dc0",
                    fallback = "Dil bilgisi bulunamadı."
                )
            )

            return
        }

        if (productCategoryId <= 0) {
            SetError(
                BBLocalization.Current.Get(
                    key = "507c8e7a-40f0-424e-b7dc-e8f5d0a3df07",
                    fallback = "Kategori bilgisi bulunamadı."
                )
            )

            return
        }

        viewModelScope.launch {
            Start("LoadDetail")

            val category =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.Detail.languageId=$languageId.productCategoryId=$productCategoryId"
                ) {
                    productCategoryRepository.GetProductCategoryByIdExtendedAsync(
                        languageId = languageId,
                        productCategoryId = productCategoryId
                    )
                }

            val childCategories =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.ChildCategories.languageId=$languageId.productCategoryId=$productCategoryId"
                ) {
                    productCategoryRepository.GetProductChildCategoriesAsync(
                        languageId = languageId,
                        productCategoryId = productCategoryId
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    CategoryResult = category,
                    ChildCategoryListResult = childCategories,
                    ErrorMessage = listOfNotNull(
                        category
                            .takeIf { result -> !result.Success }
                            ?.Message,
                        childCategories
                            .takeIf { result -> !result.Success }
                            ?.Message
                    ).firstOrNull()
                )
            }
        }
    }

    fun LoadWholesaleProductCategoryContents(
        languageId: Int,
        productCategoryId: Int,
        groupCount: Int = 3,
        productCount: Int = 4
    ) {
        if (
            languageId <= 0 ||
            productCategoryId <= 0
        ) {
            _state.update {
                it.copy(
                    IsCategoryContentsLoading = false,
                    WholesaleProductCategoryContentsResult = null,
                    CategoryContentsErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCategoryContentsLoading = true,
                    CategoryContentsErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.WholesaleProductCategoryContents.languageId=$languageId.productCategoryId=$productCategoryId.groupCount=$groupCount.productCount=$productCount"
                ) {
                    wholesaleProductCategoryContentRepository.GetWholesaleProductCategoryContentsAsync(
                        languageId = languageId,
                        productCategoryId = productCategoryId,
                        groupCount = groupCount,
                        productCount = productCount
                    )
                }

            _state.update {
                it.copy(
                    IsCategoryContentsLoading = false,
                    WholesaleProductCategoryContentsResult = response,
                    CategoryContentsErrorMessage = response.Message
                        .takeIf { !response.Success }
                )
            }
        }
    }

    fun LoadWholesaleProductCategoryContentPage(
        productCategoryContentGroupId: Int,
        page: Int = 1,
        pageSize: Int = 20
    ) {
        if (productCategoryContentGroupId <= 0) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCategoryContentListLoading = true,
                    CategoryContentListErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.WholesaleProductCategoryContentPage.groupId=$productCategoryContentGroupId.page=$page.pageSize=$pageSize"
                ) {
                    wholesaleProductCategoryContentRepository.GetWholesaleProductCategoryContentsPagedAsync(
                        productCategoryContentGroupId = productCategoryContentGroupId,
                        page = page,
                        pageSize = pageSize
                    )
                }

            _state.update {
                it.copy(
                    IsCategoryContentListLoading = false,
                    WholesaleProductCategoryContentListResult = response,
                    CategoryContentListErrorMessage = response.Message
                        .takeIf { !response.Success }
                )
            }
        }
    }

    fun LoadSpecialContents(
        languageId: Int,
        count: Int = 6
    ) {
        if (
            languageId <= 0 ||
            count <= 0
        ) {
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsSpecialContentsLoading = true,
                    SpecialContentsErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.SpecialContents.languageId=$languageId.count=$count"
                ) {
                    wholesaleHomepageSpecialContentRepository.GetHomepageSpecialContents(
                        languageId = languageId,
                        count = count
                    )
                }

            _state.update {
                it.copy(
                    IsSpecialContentsLoading = false,
                    SpecialContentsResult = response,
                    SpecialContentsErrorMessage = response.Message
                        .takeIf { !response.Success }
                )
            }
        }
    }

    fun LoadCategorySlider(
        languageId: Int,
        productCategoryId: Int
    ) {
        if (
            languageId <= 0 ||
            productCategoryId <= 0
        ) {
            _state.update {
                it.copy(
                    IsCategorySliderLoading = false,
                    CategorySliderResult = null,
                    CategorySliderErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCategorySliderLoading = true,
                    CategorySliderErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.Slider.languageId=$languageId.productCategoryId=$productCategoryId"
                ) {
                    wholesaleProductCategorySliderRepository.GetWholesaleProductCategorySlider(
                        languageId = languageId,
                        productCategoryId = productCategoryId
                    )
                }

            _state.update {
                it.copy(
                    IsCategorySliderLoading = false,
                    CategorySliderResult = response,
                    CategorySliderErrorMessage = response.Message
                        .takeIf { !response.Success }
                )
            }
        }
    }

    fun LoadCategorySuppliers(
        languageId: Int,
        productCategoryId: Int,
        count: Int = 2
    ) {
        if (
            languageId <= 0 ||
            productCategoryId <= 0 ||
            count <= 0
        ) {
            _state.update {
                it.copy(
                    IsCategorySuppliersLoading = false,
                    CategorySuppliersResult = null,
                    CategorySuppliersErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsCategorySuppliersLoading = true,
                    CategorySuppliersErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = "b2b.Category.Suppliers.languageId=$languageId.productCategoryId=$productCategoryId.count=$count"
                ) {
                    wholesaleProductCategorySupplierRepository.GetWholesaleProductCategorySuppliers(
                        languageId = languageId,
                        productCategoryId = productCategoryId,
                        count = count
                    )
                }

            _state.update {
                it.copy(
                    IsCategorySuppliersLoading = false,
                    CategorySuppliersResult = response,
                    CategorySuppliersErrorMessage = response.Message
                        .takeIf { !response.Success }
                )
            }
        }
    }

    fun GetCategoryScopeIds(
        productCategoryId: Int
    ): List<Int> {
        if (productCategoryId <= 0) {
            return emptyList()
        }

        val categories =
            State.value.Categories

        if (categories.isEmpty()) {
            return listOf(productCategoryId)
        }

        val childrenByParent =
            categories.groupBy {
                it.ParentId
            }

        val result =
            LinkedHashSet<Int>()

        val queue =
            ArrayDeque<Int>()

        result.add(productCategoryId)
        queue.add(productCategoryId)

        while (queue.isNotEmpty()) {
            val parentId =
                queue.removeFirst()

            childrenByParent[parentId]
                .orEmpty()
                .forEach { child ->
                    if (
                        child.ProductCategoryId > 0 &&
                        result.add(child.ProductCategoryId)
                    ) {
                        queue.add(child.ProductCategoryId)
                    }
                }
        }

        return result.toList()
    }

    fun Clear() {
        _state.update {
            CategoryControllerState()
        }
    }

    private fun Start(
        action: String
    ) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = action,
                ErrorMessage = null
            )
        }
    }

    private fun SetError(
        message: String
    ) {
        _state.update {
            it.copy(
                IsLoading = false,
                CurrentAction = null,
                ErrorMessage = message
            )
        }
    }
}