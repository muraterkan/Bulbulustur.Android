package com.bulbulustur.android.Application.Areas.b2c.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.BasketDTO
import com.bulbulustur.android.businesslayer.Core.DTO.BasketInsertResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketQuantityUpdateResponse
import com.bulbulustur.android.businesslayer.Core.DTO.BasketSummaryDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IBasketRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.BasketInsertRequest
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.BasketQuantityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BasketControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val BasketListResult: Result<List<BasketDTO>>? = null,
    val BasketSummaryResult: Result<BasketSummaryDTO>? = null,
    val InsertResult: Result<BasketInsertResponse>? = null,
    val QuantityUpdateResult: Result<BasketQuantityUpdateResponse>? = null,
    val DeleteResult: Result<Any?>? = null,
    val MoveToFavoriteResult: Result<Any?>? = null,
    val ErrorMessage: String? = null
) {

    val BasketItems: List<BasketDTO>
        get() =
            BasketListResult
                ?.Data
                .orEmpty()

    val BasketSummary: BasketSummaryDTO?
        get() =
            BasketSummaryResult
                ?.Data

    val ItemCount: Int
        get() =
            InsertResult
                ?.Data
                ?.ItemCount
                ?: BasketItems.size

    val TotalQuantity: Int
        get() =
            InsertResult
                ?.Data
                ?.TotalQuantity
                ?: BasketItems.sumOf { basket ->
                    basket.Quantity
                }
}

class BasketController(
    private val executeService: IExecuteService,
    private val basketRepository: IBasketRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            BasketControllerState()
        )

    val State: StateFlow<BasketControllerState> =
        _state.asStateFlow()

    fun List(
        memberId: Int,
        count: Int = 150
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "List"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        ""
                ) {
                    basketRepository.GetBasketsAsync(
                        memberId =
                            memberId,
                        count =
                            count
                    )
                }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    BasketListResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun Summary(
        memberId: Int
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "Summary"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        ""
                ) {
                    basketRepository.GetBasketSummaryAsync(
                        memberId =
                            memberId
                    )
                }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    BasketSummaryResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun AddToBasket(
        memberId: Int,
        priceId: Int,
        quantity: Int = 1,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        if (priceId <= 0) {
            _state.update { currentState ->
                currentState.copy(
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "7a2833af-d6f0-4cdb-afe8-9f61bb82b1a5", fallback = "Ürün fiyat bilgisi bulunamadı.")
                )
            }

            return
        }

        val safeQuantity =
            quantity.coerceAtLeast(
                1
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "AddToBasket"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "b2c.Basket.AddToBasket"
                ) {
                    basketRepository.InsertBasketItemAsync(
                        memberId =
                            memberId,
                        request =
                            BasketInsertRequest(
                                PriceId =
                                    priceId,
                                Quantity =
                                    safeQuantity
                            )
                    )
                }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    InsertResult =
                        response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateQuantity(
        memberId: Int,
        basketId: Int,
        quantity: Int
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        if (basketId <= 0) {
            _state.update { currentState ->
                currentState.copy(
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "f07713a9-374e-47b6-bc35-7d78212cd17b", fallback = "Geçerli bir sepet satırı bulunamadı.")
                )
            }

            return
        }

        val safeQuantity =
            quantity.coerceAtLeast(
                0
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "UpdateQuantity"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "b2c.Basket.UpdateQuantity"
                ) {
                    basketRepository.UpdateBasketQuantityAsync(
                        memberId =
                            memberId,
                        request =
                            BasketQuantityUpdateModel(
                                BasketId =
                                    basketId,
                                Quantity =
                                    safeQuantity
                            )
                    )

                }

            val responseData = response.Data

            _state.update { currentState ->
                val updatedItems =
                    if (
                        response.Success &&
                        responseData != null
                    ) {
                        ApplyQuantityUpdate(
                            basketItems =
                                currentState.BasketItems,
                            response =
                                responseData
                        )
                    }else {
                        currentState.BasketItems
                    }

                currentState.copy(
                    IsLoading =
                        false,
                    QuantityUpdateResult =
                        response,
                    BasketListResult =
                        if (response.Success) {
                            Result(
                                Success =
                                    true,
                                Data =
                                    updatedItems
                            )
                        } else {
                            currentState.BasketListResult
                        },
                    BasketSummaryResult =
                        if (
                            response.Success &&
                            responseData != null
                        ) {
                            Result(
                                Success =
                                    true,
                                Data =
                                    responseData.Summary
                            )
                        } else {
                            currentState.BasketSummaryResult
                        },
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun Delete(
        memberId: Int,
        basketId: Int
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        if (basketId <= 0) {
            _state.update { currentState ->
                currentState.copy(
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "f07713a9-374e-47b6-bc35-7d78212cd17b", fallback = "Geçerli bir sepet satırı bulunamadı.")
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "Delete"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "b2c.Basket.Delete"
                ) {
                    basketRepository.DeleteBasketItemAsync(
                        memberId =
                            memberId,
                        basketId =
                            basketId
                    )
                }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    DeleteResult =
                        response,
                    BasketListResult =
                        if (response.Success) {
                            Result(
                                Success =
                                    true,
                                Data =
                                    currentState.BasketItems.filterNot { basket ->
                                        basket.BasketId ==
                                                basketId
                                    }
                            )
                        } else {
                            currentState.BasketListResult
                        },
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                Summary(memberId = memberId)
            }
        }
    }

    fun MoveToFavorite(
        memberId: Int,
        basketId: Int
    ) {
        if (memberId <= 0) {
            SetAuthenticationError()
            return
        }

        if (basketId <= 0) {
            _state.update { currentState ->
                currentState.copy(
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "f07713a9-374e-47b6-bc35-7d78212cd17b", fallback = "Geçerli bir sepet satırı bulunamadı.")
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "MoveToFavorite"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "b2c.Basket.MoveToFavorite"
                ) {
                    basketRepository.MoveBasketToFavoriteAsync(
                        basketId =
                            basketId
                    )
                }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    MoveToFavoriteResult =
                        response,
                    BasketListResult =
                        if (response.Success) {
                            Result(
                                Success =
                                    true,
                                Data =
                                    currentState.BasketItems.filterNot { basket ->
                                        basket.BasketId ==
                                                basketId
                                    }
                            )
                        } else {
                            currentState.BasketListResult
                        },
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                Summary(memberId = memberId)
            }
        }
    }

    fun Refresh(
        memberId: Int,
        count: Int = 150
    ) {
        List(
            memberId =
                memberId,
            count =
                count
        )

        Summary(
            memberId =
                memberId
        )
    }

    fun ClearFeedback() {
        _state.update { currentState ->
            currentState.copy(
                InsertResult =
                    null,
                QuantityUpdateResult =
                    null,
                DeleteResult =
                    null,
                MoveToFavoriteResult =
                    null,
                ErrorMessage =
                    null
            )
        }
    }

    fun Clear() {
        _state.value =
            BasketControllerState()
    }

    private fun SetLoading(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    true,
                CurrentAction =
                    currentAction,
                ErrorMessage =
                    null
            )
        }
    }

    private fun SetAuthenticationError() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                ErrorMessage =
                    BBLocalization.Current.Get(key = "e1783e80-755c-42bb-b996-6af48da03db2", fallback = "Sepet işlemi için giriş yapmalısınız.")
            )
        }
    }

    private fun ApplyQuantityUpdate(
        basketItems: List<BasketDTO>,
        response: BasketQuantityUpdateResponse
    ): List<BasketDTO> {
        if (response.Removed) {
            return basketItems.filterNot { basket ->
                basket.BasketId ==
                        response.BasketId
            }
        }

        return basketItems.map { basket ->
            if (
                basket.BasketId ==
                response.BasketId
            ) {
                basket.copy(
                    Quantity =
                        response.Quantity,
                    TotalPrice =
                        response.LineTotal
                )
            } else {
                basket
            }
        }
    }
}