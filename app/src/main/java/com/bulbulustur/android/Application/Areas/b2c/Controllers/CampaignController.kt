package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CampaignDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICampaignRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
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
    private val campaignRepository: ICampaignRepository,
    private val productRepository: IProductRepository
) : BaseController() {

    private val _state = MutableStateFlow(CampaignControllerState())
    val State: StateFlow<CampaignControllerState> = _state.asStateFlow()

    fun LoadCampaigns(
        languageId: Int,
        count: Int = 20
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val result = campaignRepository.GetCampaignsAsync(
                languageId = languageId,
                count = count
            )

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaigns = result.Data ?: emptyList(),
                    ErrorMessage = result.Message
                )
            }
        }
    }

    fun LoadCampaignsByCategory(
        languageId: Int,
        categoryId: Int,
        count: Int = 8
    ) {
        if (
            languageId <= 0 ||
            categoryId <= 0 ||
            count <= 0
        ) {
            _state.update {
                it.copy(
                    Campaigns = emptyList()
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    Campaigns = emptyList(),
                    ErrorMessage = null
                )
            }

            val result = campaignRepository.GetCampaignsByCategoryAsync(
                languageId = languageId,
                categoryId = categoryId,
                count = count
            )

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaigns = result.Data ?: emptyList(),
                    ErrorMessage = result.Message
                )
            }
        }
    }

    fun LoadCampaignDetail(
        languageId: Int,
        campaignId: Int
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    Campaign = null,
                    ErrorMessage = null
                )
            }

            val result = campaignRepository.GetCampaignByIdExtendedAsync(
                languageId = languageId,
                campaignId = campaignId
            )

            val campaign = result.Data
            val products = campaign?.CampaignProducts.orEmpty()

            val variantIds = products
                .map { it.VariantId }
                .filter { it > 0 }
                .distinct()

            val picturesResult = if (variantIds.isNotEmpty()) {
                productRepository.GetDefaultProductVariantPicturesAsync(
                    variantIds
                )
            } else {
                null
            }

            val pictures = picturesResult?.Data ?: emptyMap()

            val productsWithPictures = products.map { product ->
                val picture =
                    pictures[product.VariantId.toString()]
                        .orEmpty()

                if (picture.isNotBlank()) {
                    product.copy(
                        DefaultPicture = picture
                    )
                } else {
                    product
                }
            }

            val campaignWithPictures =
                campaign?.copy(
                    CampaignProducts = productsWithPictures
                )

            _state.update {
                it.copy(
                    IsLoading = false,
                    Campaign = campaignWithPictures,
                    ErrorMessage = result.Message
                )
            }
        }
    }
}