package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageFeaturedProductDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleHomepageFeaturedProductRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleHomepageSpecialContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeControllerState(
    val IsLoading: Boolean = false,
    val FeaturedProducts: List<WholesaleHomepageFeaturedProductDTO> = emptyList(),
    val FeaturedProductsAll: List<WholesaleHomepageFeaturedProductDTO> = emptyList(),
    val IsFeaturedProductsLoading: Boolean = false,
    val SpecialContents: List<WholesaleHomepageSpecialContentDTO> = emptyList(),
    val ErrorMessage: String? = null
)

class HomeController(
    private val wholesaleHomepageFeaturedProductRepository: IWholesaleHomepageFeaturedProductRepository,
    private val wholesaleHomepageSpecialContentRepository: IWholesaleHomepageSpecialContentRepository
) : BaseController() {

    private val _state = MutableStateFlow(HomeControllerState())
    val State: StateFlow<HomeControllerState> = _state.asStateFlow()

    fun Load(
        languageId: Int,
        featuredProductCount: Int = 12,
        specialContentCount: Int = 6
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val featuredProductsResult =
                wholesaleHomepageFeaturedProductRepository
                    .GetHomepageFeaturedProductsAsync(featuredProductCount)

            val specialContentsResult =
                wholesaleHomepageSpecialContentRepository
                    .GetHomepageSpecialContents(
                        languageId,
                        specialContentCount
                    )

            _state.update {
                it.copy(
                    IsLoading = false,
                    FeaturedProducts =
                        featuredProductsResult.Data ?: emptyList(),
                    SpecialContents =
                        specialContentsResult.Data ?: emptyList()
                )
            }
        }
    }

    fun LoadFeaturedProducts(
        count: Int = 100
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsFeaturedProductsLoading = true,
                    ErrorMessage = null
                )
            }

            val result =
                wholesaleHomepageFeaturedProductRepository
                    .GetHomepageFeaturedProductsAsync(count)

            _state.update {
                it.copy(
                    IsFeaturedProductsLoading = false,
                    FeaturedProductsAll = result.Data ?: emptyList()
                )
            }
        }
    }
}