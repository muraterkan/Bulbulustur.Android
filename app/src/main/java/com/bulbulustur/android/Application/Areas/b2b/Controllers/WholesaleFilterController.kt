package com.bulbulustur.android.Application.Areas.b2b.Controllers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class WholesaleFilterOption(
    val Key: String = "",
    val Text: String = "",
    val IsSelected: Boolean = false
)

data class WholesaleFilterGroup(
    val Key: String = "",
    val Title: String = "",
    val Options: List<WholesaleFilterOption> = emptyList(),
    val IsSearchable: Boolean = false
)

data class WholesaleFilterControllerState(
    val ProductCategoryId: Int = 0,
    val LanguageId: Int = 1,
    val Groups: List<WholesaleFilterGroup> = emptyList(),
    val SelectedGroupKey: String = "",
    val SelectedFilterCount: Int = 0,
    val MinPrice: Double? = null,
    val MaxPrice: Double? = null,
    val SortOrder: String = "",
    val IsLoading: Boolean = false,
    val ErrorMessage: String? = null
)

sealed interface WholesaleFilterControllerEvent {
    data class Initialize(
        val ProductCategoryId: Int,
        val LanguageId: Int
    ) : WholesaleFilterControllerEvent

    data class OpenGroup(
        val GroupKey: String
    ) : WholesaleFilterControllerEvent

    data class ToggleOption(
        val GroupKey: String,
        val OptionKey: String
    ) : WholesaleFilterControllerEvent

    data class SetPriceRange(
        val MinPrice: Double?,
        val MaxPrice: Double?
    ) : WholesaleFilterControllerEvent

    data class SetSortOrder(
        val SortOrder: String
    ) : WholesaleFilterControllerEvent

    data object Clear : WholesaleFilterControllerEvent
}

class WholesaleFilterController : ViewModel() {

    private val _state = MutableStateFlow(WholesaleFilterControllerState())
    val State: StateFlow<WholesaleFilterControllerState> = _state.asStateFlow()

    fun OnEvent(event: WholesaleFilterControllerEvent) {
        when (event) {
            is WholesaleFilterControllerEvent.Initialize -> {
                _state.update {
                    it.copy(
                        ProductCategoryId = event.ProductCategoryId,
                        LanguageId = event.LanguageId
                    )
                }
            }

            is WholesaleFilterControllerEvent.OpenGroup -> {
                _state.update {
                    it.copy(
                        SelectedGroupKey = event.GroupKey
                    )
                }
            }

            is WholesaleFilterControllerEvent.ToggleOption -> {
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

            is WholesaleFilterControllerEvent.SetPriceRange -> {
                _state.update {
                    it.copy(
                        MinPrice = event.MinPrice,
                        MaxPrice = event.MaxPrice
                    )
                }
            }

            is WholesaleFilterControllerEvent.SetSortOrder -> {
                _state.update {
                    it.copy(
                        SortOrder = event.SortOrder
                    )
                }
            }

            WholesaleFilterControllerEvent.Clear -> {
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
