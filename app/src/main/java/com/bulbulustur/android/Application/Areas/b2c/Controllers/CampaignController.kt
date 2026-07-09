package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CampaignControllerState(
    val IsLoading: Boolean = false,
    val Campaigns: List<CampaignDTO> = emptyList(),
    val Campaign: CampaignDTO? = null,
    val ErrorMessage: String? = null
)

class CampaignController(
    private val campaignRepository: ICampaignRepository
) : BaseController() {

    private val _state = MutableStateFlow(CampaignControllerState())
    val State: StateFlow<CampaignControllerState> = _state.asStateFlow()

    fun LoadCampaigns(languageId: Int, count: Int = 20) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val result = campaignRepository.GetCampaignsAsync(languageId, count)

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaigns = result.Data ?: emptyList(),
                    ErrorMessage = result.Message
                )
            }
        }
    }

    fun LoadCampaignDetail(languageId: Int, campaignId: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    Campaign = null,
                    ErrorMessage = null
                )
            }

            val result = campaignRepository.GetCampaignByIdExtendedAsync(languageId, campaignId)

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaign = result.Data,
                    ErrorMessage = result.Message
                )
            }
        }
    }
}
