package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.DealsOfTheDayDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IDealsOfTheDayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DealsOfTheDayControllerState(
    val IsLoading: Boolean = false,
    val DealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    val CategoryDealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    val ErrorMessage: String? = null
)

class DealsOfTheDayController(
    private val dealsOfTheDayRepository: IDealsOfTheDayRepository
) : BaseController() {

    private val _state = MutableStateFlow(DealsOfTheDayControllerState())
    val State: StateFlow<DealsOfTheDayControllerState> = _state.asStateFlow()

    fun Load(languageId: Int, count: Int = 50) {
        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null) }

            val result = dealsOfTheDayRepository.GetDealsOfTheDaysAsync(languageId, count)

            _state.update {
                it.copy(
                    IsLoading = false,
                    DealsOfTheDays = result.Data ?: emptyList(),
                    ErrorMessage = result.Message.takeIf { !result.Success }
                )
            }
        }
    }

    fun LoadByProductCategoryList(languageId: Int, productCategoryIds: List<Int>, count: Int = 6) {
        if (languageId <= 0 || productCategoryIds.isEmpty()) {
            _state.update { it.copy(CategoryDealsOfTheDays = emptyList()) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(IsLoading = true, ErrorMessage = null) }

            val result = dealsOfTheDayRepository.GetDealsOfTheDaysByProductCategoryListAsync(
                languageId = languageId,
                productCategoryIds = productCategoryIds,
                count = count
            )

            _state.update {
                it.copy(
                    IsLoading = false,
                    CategoryDealsOfTheDays = result.Data ?: emptyList(),
                    ErrorMessage = result.Message.takeIf { !result.Success }
                )
            }
        }
    }
}
