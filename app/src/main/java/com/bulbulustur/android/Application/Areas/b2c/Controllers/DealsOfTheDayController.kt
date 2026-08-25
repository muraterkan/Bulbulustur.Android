package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IDealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DealsOfTheDayControllerState(
    val IsLoading: Boolean = false,
    val DealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    val ErrorMessage: String? = null
)

class DealsOfTheDayController(
    private val dealsOfTheDayRepository: IDealsOfTheDayRepository,
    private val productRepository: IProductRepository
) : BaseController() {

    private val _state = MutableStateFlow(DealsOfTheDayControllerState())
    val State: StateFlow<DealsOfTheDayControllerState> = _state.asStateFlow()

    fun Load(languageId: Int, count: Int = 50) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val result =
                dealsOfTheDayRepository.GetDealsOfTheDaysAsync(
                    languageId,
                    count
                )

            val deals = result.Data ?: emptyList()
            val variantIds = deals.map { it.VariantId }.filter { it > 0 }.distinct()

            val picturesResult = if (variantIds.isNotEmpty()) {
                productRepository.GetDefaultProductVariantPicturesAsync(variantIds)
            } else {
                null
            }

            val pictures = picturesResult?.Data ?: emptyMap()

            val dealsWithPictures = deals.map { deal ->
                val picture = pictures[deal.VariantId.toString()].orEmpty()

                if (picture.isNotBlank()) {
                    deal.copy(DefaultPicture = picture)
                } else {
                    deal
                }
            }

            _state.update {
                it.copy(
                    IsLoading = false,
                    DealsOfTheDays = dealsWithPictures,
                    ErrorMessage = result.Message
                )
            }
        }
    }
}