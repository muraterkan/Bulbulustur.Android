package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleProductRepository
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
    val ProductSearchResult: Result<PaginatedList<WholesaleProductDTO>>? = null,
    val ErrorMessage: String? = null
)

class SearchController(
    private val executeService: IExecuteService,
    private val wholesaleProductRepository: IWholesaleProductRepository = WholesaleProductRepository()
) : BaseController() {

    private val _state = MutableStateFlow(SearchControllerState())
    val State: StateFlow<SearchControllerState> = _state.asStateFlow()

    fun SearchProducts(companyId: Int = 0, key: String, page: Int = 1, pageSize: Int = 20, sortOrder: String = "Default_Asc") {
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
                cacheKey = "b2b.Search.Products.company.$companyId.key.$searchKey.page.$page.size.$pageSize.sort.$sortOrder"
            ) {
                wholesaleProductRepository.GetSearchingProductsAsync(
                    companyId = companyId,
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
