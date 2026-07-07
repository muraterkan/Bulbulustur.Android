package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductHomepageSpecialContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductHomepageSpecialContentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeControllerState(
    val IsLoading: Boolean = false,
    val Campaigns: List<CampaignDTO> = emptyList(),
    val DealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    val SpecialContents: List<ProductHomepageSpecialContentDTO> = emptyList(),
    val ErrorMessage: String? = null
)

class HomeController(
    private val campaignRepository: ICampaignRepository,
    private val dealsOfTheDayRepository: IDealsOfTheDayRepository,
    private val productHomepageSpecialContentRepository: IProductHomepageSpecialContentRepository
) : BaseController() {

    private val _state = MutableStateFlow(HomeControllerState())
    val State: StateFlow<HomeControllerState> = _state.asStateFlow()

    fun Load(languageId: Int, campaignCount: Int = 3, dealsOfTheDayCount: Int = 8, specialContentCount: Int = 5) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val campaignsResult = campaignRepository.GetCampaignsAsync(languageId, campaignCount)
            val dealsOfTheDaysResult = dealsOfTheDayRepository.GetDealsOfTheDaysAsync(languageId, dealsOfTheDayCount)
            val specialContentsResult = productHomepageSpecialContentRepository.GetHomepageSpecialContentsAsync(languageId, specialContentCount)

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaigns = campaignsResult.Data ?: emptyList(),
                    DealsOfTheDays = dealsOfTheDaysResult.Data ?: emptyList(),
                    SpecialContents = specialContentsResult.Data ?: emptyList()
                )
            }
        }
    }
}