package com.bulbulustur.android.Application.Areas.b2c.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductReviewControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,

    val Reviews: List<ReviewDTO> = emptyList(),
    val ReviewsResult: Result<PaginatedList<ReviewDTO>>? = null,

    val SourceType: String = "PRODUCT",
    val SourceId: Int = 0,
    val VariantId: Int = 0,

    val CurrentPage: Int = 1,
    val PageSize: Int = 10,
    val HasNextPage: Boolean = false,

    val ErrorMessage: String? = null
)

sealed interface ProductReviewControllerEvent {

    data class Load(
        val SourceType: String = "PRODUCT",
        val SourceId: Int,
        val VariantId: Int = 0,
        val PageSize: Int = 10
    ) : ProductReviewControllerEvent

    data object LoadMore :
        ProductReviewControllerEvent

    data object Refresh :
        ProductReviewControllerEvent

    data object Clear :
        ProductReviewControllerEvent

    data object ClearError :
        ProductReviewControllerEvent
}

class ProductReviewController(
    private val executeService: IExecuteService,
    private val reviewRepository: IReviewRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            ProductReviewControllerState()
        )

    val State: StateFlow<ProductReviewControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: ProductReviewControllerEvent
    ) {
        when (event) {
            is ProductReviewControllerEvent.Load -> {
                List(
                    sourceType =
                        event.SourceType,
                    sourceId =
                        event.SourceId,
                    variantId =
                        event.VariantId,
                    page =
                        1,
                    pageSize =
                        event.PageSize,
                    append =
                        false
                )
            }

            ProductReviewControllerEvent.LoadMore -> {
                LoadMore()
            }

            ProductReviewControllerEvent.Refresh -> {
                Refresh()
            }

            ProductReviewControllerEvent.Clear -> {
                Clear()
            }

            ProductReviewControllerEvent.ClearError -> {
                ClearError()
            }
        }
    }

    fun List(
        sourceType: String = "PRODUCT",
        sourceId: Int,
        variantId: Int = 0,
        page: Int = 1,
        pageSize: Int = 10,
        append: Boolean = false
    ) {
        if (
            sourceType.isBlank() ||
            sourceId <= 0 ||
            page <= 0 ||
            pageSize <= 0
        ) {
            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "List",
                    Reviews =
                        if (append) {
                            it.Reviews
                        } else {
                            emptyList()
                        },
                    ReviewsResult =
                        null,
                    HasNextPage =
                        false,
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "a6eaf805-9df7-4fb3-a4ad-85bd6ecad250", fallback = "Geçerli değerlendirme parametreleri bulunamadı.")
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    if (append) {
                        "LoadMore"
                    } else {
                        "List"
                    }
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "commerceSupport.Review.GetReviewsAsync." +
                                "sourceType=$sourceType." +
                                "sourceId=$sourceId." +
                                "variantId=$variantId." +
                                "page=$page." +
                                "pageSize=$pageSize"
                ) {
                    reviewRepository.GetReviewsAsync(
                        sourceType =
                            sourceType,
                        sourceId =
                            sourceId,
                        variantId =
                            variantId,
                        page =
                            page,
                        pageSize =
                            pageSize
                    )
                }

            val incomingReviews =
                response.Data
                    ?.Items
                    ?: emptyList()

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        if (append) {
                            "LoadMore"
                        } else {
                            "List"
                        },
                    Reviews =
                        if (
                            append &&
                            response.Success
                        ) {
                            (
                                    currentState.Reviews +
                                            incomingReviews
                                    ).distinctBy {
                                    it.ReviewId
                                }
                        } else if (response.Success) {
                            incomingReviews
                        } else {
                            currentState.Reviews
                        },
                    ReviewsResult =
                        response,
                    SourceType =
                        sourceType,
                    SourceId =
                        sourceId,
                    VariantId =
                        variantId,
                    CurrentPage =
                        if (response.Success) {
                            page
                        } else {
                            currentState.CurrentPage
                        },
                    PageSize =
                        pageSize,
                    HasNextPage =
                        response.Success &&
                                response.Data?.HasNextPage == true,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
                                ?: BBLocalization.Current.Get(key = "4a26c4e4-5c82-4c19-9e36-08cb0dadf54f", fallback = "Değerlendirmeler alınamadı.")
                        }
                )
            }
        }
    }

    fun LoadMore() {
        val currentState =
            _state.value

        if (
            currentState.IsLoading ||
            !currentState.HasNextPage ||
            currentState.SourceId <= 0
        ) {
            return
        }

        List(
            sourceType =
                currentState.SourceType,
            sourceId =
                currentState.SourceId,
            variantId =
                currentState.VariantId,
            page =
                currentState.CurrentPage + 1,
            pageSize =
                currentState.PageSize,
            append =
                true
        )
    }

    fun Refresh() {
        val currentState =
            _state.value

        if (currentState.SourceId <= 0) {
            return
        }

        List(
            sourceType =
                currentState.SourceType,
            sourceId =
                currentState.SourceId,
            variantId =
                currentState.VariantId,
            page =
                1,
            pageSize =
                currentState.PageSize,
            append =
                false
        )
    }

    fun Clear() {
        _state.value =
            ProductReviewControllerState()
    }

    fun ClearError() {
        _state.update {
            it.copy(
                ErrorMessage =
                    null
            )
        }
    }

    private fun StartLoading(
        actionName: String
    ) {
        _state.update {
            it.copy(
                IsLoading =
                    true,
                CurrentAction =
                    actionName,
                ErrorMessage =
                    null
            )
        }
    }
}