package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.ProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import android.util.Log
import kotlinx.coroutines.launch

data class SearchControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val ProductSearchResult: Result<PaginatedList<ProductDTO>>? = null,
    val ErrorMessage: String? = null
)

class SearchController(
    private val executeService: IExecuteService,
    private val productRepository: IProductRepository = ProductRepository()
) : BaseController() {

    private val _state = MutableStateFlow(SearchControllerState())
    val State: StateFlow<SearchControllerState> = _state.asStateFlow()

    fun SearchProducts(storeId: Int = 0, key: String, page: Int = 1, pageSize: Int = 20, sortOrder: String = "Default_Asc") {
        val searchKey = key.trim()

        if (searchKey.length < 3) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = "SearchProducts",
                    ErrorMessage = "Arama anahtarı en az 3 karakter olmalıdır."
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null,
                    CurrentAction = "SearchProducts"
                )
            }

            val response = executeService.GetAsync(
                cacheKey = "b2c.Search.Products.store.$storeId.key.$searchKey.page.$page.size.$pageSize.sort.$sortOrder"
            ) {
                productRepository.GetSearchingProductsAsync(
                    storeId = storeId,
                    key = searchKey,
                    page = page,
                    pageSize = pageSize,
                    sortOrder = sortOrder
                )
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    ProductSearchResult = response
                )
            }
        }
    }

    fun ClearSearch() {
        _state.update {
            SearchControllerState()
        }
    }
}
