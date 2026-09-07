package com.bulbulustur.android.Application.Areas.b2c.Controllers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class RetailFilterOption(
    val Key: String = "",
    val Text: String = "",
    val IsSelected: Boolean = false
)

data class RetailFilterGroup(
    val Key: String = "",
    val Title: String = "",
    val Options: List<RetailFilterOption> = emptyList(),
    val IsSearchable: Boolean = false
)

data class RetailFilterControllerState(
    val ProductCategoryId: Int = 0,
    val LanguageId: Int = 1,
    val Groups: List<RetailFilterGroup> = emptyList(),
    val SelectedGroupKey: String = "",
    val SelectedFilterCount: Int = 0,
    val MinPrice: Double? = null,
    val MaxPrice: Double? = null,
    val SortOrder: String = "",
    val IsLoading: Boolean = false,
    val ErrorMessage: String? = null
)

sealed interface RetailFilterControllerEvent {
    data class Initialize(
        val ProductCategoryId: Int,
        val LanguageId: Int
    ) : RetailFilterControllerEvent

    data class OpenGroup(
        val GroupKey: String
    ) : RetailFilterControllerEvent

    data class ToggleOption(
        val GroupKey: String,
        val OptionKey: String
    ) : RetailFilterControllerEvent

    data class SetPriceRange(
        val MinPrice: Double?,
        val MaxPrice: Double?
    ) : RetailFilterControllerEvent

    data class SetSortOrder(
        val SortOrder: String
    ) : RetailFilterControllerEvent

    data object Clear : RetailFilterControllerEvent
}

class RetailFilterController : ViewModel() {

    private val _state = MutableStateFlow(RetailFilterControllerState())
    val State: StateFlow<RetailFilterControllerState> = _state.asStateFlow()

    fun OnEvent(event: RetailFilterControllerEvent) {
        when (event) {
            is RetailFilterControllerEvent.Initialize -> {
                _state.update {
                    it.copy(
                        ProductCategoryId = event.ProductCategoryId,
                        LanguageId = event.LanguageId
                    )
                }
            }

            is RetailFilterControllerEvent.OpenGroup -> {
                _state.update {
                    it.copy(
                        SelectedGroupKey = event.GroupKey
                    )
                }
            }

            is RetailFilterControllerEvent.ToggleOption -> {
                _state.update { current ->
                    val updatedGroups = current.Groups.map { group ->
                        if (group.Key != event.GroupKey) {
                            group
                        } else {
                            group.copy(
                                Options = group.Options.map { option ->
                                    if (option.Key != event.OptionKey) {
                                        option
                                    } else {
                                        option.copy(
                                            IsSelected = !option.IsSelected
                                        )
                                    }
                                }
                            )
                        }
                    }

                    current.copy(
                        Groups = updatedGroups,
                        SelectedFilterCount = updatedGroups.sumOf { group ->
                            group.Options.count { option ->
                                option.IsSelected
                            }
                        }
                    )
                }
            }

            is RetailFilterControllerEvent.SetPriceRange -> {
                _state.update {
                    it.copy(
                        MinPrice = event.MinPrice,
                        MaxPrice = event.MaxPrice
                    )
                }
            }

            is RetailFilterControllerEvent.SetSortOrder -> {
                _state.update {
                    it.copy(
                        SortOrder = event.SortOrder
                    )
                }
            }

            RetailFilterControllerEvent.Clear -> {
                _state.update { current ->
                    current.copy(
                        Groups = current.Groups.map { group ->
                            group.copy(
                                Options = group.Options.map { option ->
                                    option.copy(IsSelected = false)
                                }
                            )
                        },
                        SelectedGroupKey = "",
                        SelectedFilterCount = 0,
                        MinPrice = null,
                        MaxPrice = null,
                        SortOrder = ""
                    )
                }
            }
        }
    }
}
