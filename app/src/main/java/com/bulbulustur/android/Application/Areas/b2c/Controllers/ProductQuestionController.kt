package com.bulbulustur.android.Application.Areas.b2c.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCustomerQuestionRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductCustomerQuestionInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProductQuestionControllerState(
    val IsLoading: Boolean = false,
    val IsSubmitting: Boolean = false,
    val CurrentAction: String? = null,

    val Questions: List<ProductCustomerQuestionDTO> =
        emptyList(),

    val QuestionsResult:
    Result<List<ProductCustomerQuestionDTO>>? =
        null,

    val InsertResult: Result<Unit>? =
        null,

    val ProductId: Int = 0,
    val Count: Int = 100,

    val SuccessMessage: String? = null,
    val ErrorMessage: String? = null
)

sealed interface ProductQuestionControllerEvent {

    data class Load(
        val ProductId: Int,
        val Count: Int = 100
    ) : ProductQuestionControllerEvent

    data class Insert(
        val LanguageId: Int,
        val MemberId: Int,
        val StoreId: Int,
        val ProductSecureKey: String,
        val ProductId: Int,
        val Question: String
    ) : ProductQuestionControllerEvent

    data object Refresh :
        ProductQuestionControllerEvent

    data object ClearInsertFeedback :
        ProductQuestionControllerEvent

    data object Clear :
        ProductQuestionControllerEvent

    data object ClearError :
        ProductQuestionControllerEvent
}

class ProductQuestionController(
    private val executeService: IExecuteService,
    private val productCustomerQuestionRepository:
    IProductCustomerQuestionRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            ProductQuestionControllerState()
        )

    val State: StateFlow<ProductQuestionControllerState> =
        _state.asStateFlow()

    fun OnEvent(
        event: ProductQuestionControllerEvent
    ) {
        when (event) {
            is ProductQuestionControllerEvent.Load -> {
                List(
                    productId =
                        event.ProductId,
                    count =
                        event.Count
                )
            }

            is ProductQuestionControllerEvent.Insert -> {
                Insert(
                    languageId =
                        event.LanguageId,
                    memberId =
                        event.MemberId,
                    storeId =
                        event.StoreId,
                    productSecureKey =
                        event.ProductSecureKey,
                    productId =
                        event.ProductId,
                    question =
                        event.Question
                )
            }

            ProductQuestionControllerEvent.Refresh -> {
                Refresh()
            }

            ProductQuestionControllerEvent.ClearInsertFeedback -> {
                ClearInsertFeedback()
            }

            ProductQuestionControllerEvent.Clear -> {
                Clear()
            }

            ProductQuestionControllerEvent.ClearError -> {
                ClearError()
            }
        }
    }

    fun List(
        productId: Int,
        count: Int = 100
    ) {
        if (
            productId <= 0 ||
            count <= 0
        ) {
            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "List",
                    Questions =
                        emptyList(),
                    QuestionsResult =
                        null,
                    ProductId =
                        productId,
                    Count =
                        count,
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "e8930a30-3c0e-43e7-a0d6-502cdfe3f484", fallback = "Geçerli ürün bilgisi bulunamadı.")
                )
            }

            return
        }

        viewModelScope.launch {
            StartLoading(
                actionName =
                    "List"
            )

            val response =
                executeService.GetAsync(
                    cacheKey =
                        "b2c.ProductQuestion." +
                                "GetProductCustomerQuestionsAsync." +
                                "productId=$productId." +
                                "count=$count"
                ) {
                    productCustomerQuestionRepository
                        .GetProductCustomerQuestionsAsync(
                            productId =
                                productId,
                            count =
                                count
                        )
                }

            _state.update {
                it.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "List",
                    Questions =
                        if (response.Success) {
                            response.Data
                                ?: emptyList()
                        } else {
                            it.Questions
                        },
                    QuestionsResult =
                        response,
                    ProductId =
                        productId,
                    Count =
                        count,
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
                                ?: BBLocalization.Current.Get(key = "0f7727b0-de6a-489e-a0d6-a7c09c835708", fallback = "Ürün soruları alınamadı.")
                        }
                )
            }
        }
    }

    fun Insert(
        languageId: Int,
        memberId: Int,
        storeId: Int,
        productSecureKey: String,
        productId: Int,
        question: String,
        onSuccess: (() -> Unit)? = null
    ) {
        val normalizedQuestion =
            question.trim()

        when {
            languageId <= 0 -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "a2538f8a-25cd-4e64-8572-75585c749dc0", fallback = "Dil bilgisi bulunamadı.")
                )

                return
            }

            memberId <= 0 -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "620e9799-25f8-4b36-9fd3-59f808d55b02", fallback = "Soru göndermek için giriş yapmalısınız.")
                )

                return
            }

            storeId <= 0 -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "a64056f3-b719-45da-8cb0-b33f95963bac", fallback = "Mağaza bilgisi bulunamadı.")
                )

                return
            }

            productId <= 0 -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "c2dbad86-bccd-49ab-af0f-8919d3adbb71", fallback = "Ürün bilgisi bulunamadı.")
                )

                return
            }

            productSecureKey.isBlank() -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "88e11e89-2b66-4f97-bd8e-15e005cc636a", fallback = "Ürün güvenlik anahtarı bulunamadı.")
                )

                return
            }

            normalizedQuestion.isBlank() -> {
                SetError(
                    message =
                        BBLocalization.Current.Get(key = "29a9a720-5de4-4502-bac7-399b23bdb059", fallback = "Ürün sorusu boş bırakılamaz.")
                )

                return
            }
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsSubmitting =
                        true,
                    CurrentAction =
                        "Insert",
                    InsertResult =
                        null,
                    SuccessMessage =
                        null,
                    ErrorMessage =
                        null
                )
            }

            /*
             * Insert işlemi cache kullanmadığı için repository doğrudan
             * çağrılıyor. Liste işlemleri ExecuteService üzerinden gider.
             */
            val response =
                productCustomerQuestionRepository
                    .InsertProductCustomerQuestionAsync(
                        languageId =
                            languageId,
                        memberId =
                            memberId,
                        model =
                            ProductCustomerQuestionInsertModel(
                                StoreId =
                                    storeId,
                                ProductSecureKey =
                                    productSecureKey,
                                ProductId =
                                    productId,
                                Question =
                                    normalizedQuestion
                            )
                    )

            _state.update {
                it.copy(
                    IsSubmitting =
                        false,
                    CurrentAction =
                        "Insert",
                    InsertResult =
                        response,
                    SuccessMessage =
                        if (response.Success) {
                            response.Message
                                ?: BBLocalization.Current.Get(key = "5978fcc2-ef28-416d-9684-fac14db2bfd9", fallback = "Sorunuz ilgili satıcıya gönderildi.")
                        } else {
                            null
                        },
                    ErrorMessage =
                        if (response.Success) {
                            null
                        } else {
                            response.Message
                                ?: BBLocalization.Current.Get(key = "8ef02491-5878-4561-a522-024ad187954d", fallback = "Ürün sorusu gönderilemedi.")
                        }
                )
            }

            if (response.Success) {
                List(
                    productId =
                        productId,
                    count =
                        _state.value.Count
                            .takeIf {
                                it > 0
                            }
                            ?: 100
                )

                onSuccess?.invoke()
            }
        }
    }

    fun Refresh() {
        val currentState =
            _state.value

        if (currentState.ProductId <= 0) {
            return
        }

        List(
            productId =
                currentState.ProductId,
            count =
                currentState.Count
        )
    }

    fun ClearInsertFeedback() {
        _state.update {
            it.copy(
                InsertResult =
                    null,
                SuccessMessage =
                    null,
                ErrorMessage =
                    null
            )
        }
    }

    fun Clear() {
        _state.value =
            ProductQuestionControllerState()
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

    private fun SetError(
        message: String
    ) {
        _state.update {
            it.copy(
                IsLoading =
                    false,
                IsSubmitting =
                    false,
                ErrorMessage =
                    message
            )
        }
    }
}