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
    val CategoryDealsOfTheDays: List<DealsOfTheDayDTO> = emptyList(),
    val ErrorMessage: String? = null
)

class DealsOfTheDayController(
    private val dealsOfTheDayRepository: IDealsOfTheDayRepository,
    private val productRepository: IProductRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            DealsOfTheDayControllerState()
        )

    val State: StateFlow<DealsOfTheDayControllerState> =
        _state.asStateFlow()

    fun Load(
        languageId: Int,
        count: Int = 50
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val result =
                dealsOfTheDayRepository.GetDealsOfTheDaysAsync(
                    languageId = languageId,
                    count = count
                )

            val dealsWithPictures =
                AttachPictures(
                    result.Data.orEmpty()
                )

            _state.update {
                it.copy(
                    IsLoading = false,
                    DealsOfTheDays = dealsWithPictures,
                    ErrorMessage =
                        result.Message.takeIf {
                            !result.Success
                        }
                )
            }
        }
    }

    fun LoadByProductCategoryList(
        languageId: Int,
        productCategoryIds: List<Int>,
        count: Int = 6
    ) {
        if (
            languageId <= 0 ||
            productCategoryIds.isEmpty()
        ) {
            _state.update {
                it.copy(
                    CategoryDealsOfTheDays = emptyList()
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    ErrorMessage = null
                )
            }

            val result =
                dealsOfTheDayRepository.GetDealsOfTheDaysByProductCategoryListAsync(
                    languageId = languageId,
                    productCategoryIds = productCategoryIds,
                    count = count
                )

            val dealsWithPictures =
                AttachPictures(
                    result.Data.orEmpty()
                )

            _state.update {
                it.copy(
                    IsLoading = false,
                    CategoryDealsOfTheDays = dealsWithPictures,
                    ErrorMessage =
                        result.Message.takeIf {
                            !result.Success
                        }
                )
            }
        }
    }

    private suspend fun AttachPictures(
        deals: List<DealsOfTheDayDTO>
    ): List<DealsOfTheDayDTO> {
        val variantIds =
            deals
                .map {
                    it.VariantId
                }
                .filter {
                    it > 0
                }
                .distinct()

        if (
            variantIds.isEmpty()
        ) {
            return deals
        }

        val picturesResult =
            productRepository.GetDefaultProductVariantPicturesAsync(
                variantIds
            )

        val pictures =
            picturesResult.Data.orEmpty()

        return deals.map { deal ->
            val picture =
                pictures[
                    deal.VariantId.toString()
                ].orEmpty()

            if (
                picture.isNotBlank()
            ) {
                deal.copy(
                    DefaultPicture = picture
                )
            } else {
                deal
            }
        }
    }
}