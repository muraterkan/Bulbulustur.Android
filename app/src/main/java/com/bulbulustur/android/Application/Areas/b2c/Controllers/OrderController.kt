package com.bulbulustur.android.Application.Areas.b2c.Controllers

import com.bulbulustur.android.Application.Localization.BBLocalization

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreLineDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReturnRequestReasonDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReturnRequestReasonRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel
import com.bulbulustur.android.businesslayer.Core.Repository.ContractRepository
import com.bulbulustur.android.businesslayer.Core.Repository.OrderRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescReturnRequestReasonRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReviewInsertModel
import com.bulbulustur.android.businesslayer.Core.Repository.ReviewRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class OrderControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val Orders: List<OrderDTO> = emptyList(),
    val OrderStores: List<OrderStoreDTO> = emptyList(),
    val OrderTracking: OrderStoreLineDTO? = null,
    val Contract: ContractDTO? = null,
    val CancelationTypes: List<SystemDescOrderCancelationTypeDTO> = emptyList(),
    val IsCancelationCompleted: Boolean = false,
    val ReturnRequestReasons: List<SystemDescReturnRequestReasonDTO> = emptyList(),
    val IsReturnRequestCompleted: Boolean = false,
    val ErrorMessage: String? = null,
    val IsReviewCompleted: Boolean = false,
)

class OrderController(
    private val orderRepository: IOrderRepository = OrderRepository(),
    private val contractRepository: IContractRepository = ContractRepository(),
    private val returnRequestRepository: IReturnRequestRepository = ReturnRequestRepository(),
    private val returnRequestReasonRepository: ISystemDescReturnRequestReasonRepository = SystemDescReturnRequestReasonRepository(),
    private val reviewRepository: IReviewRepository = ReviewRepository()
) : BaseController() {

    private val _state = MutableStateFlow(OrderControllerState())
    val State: StateFlow<OrderControllerState> = _state.asStateFlow()

    fun GetOrdersByMemberIdAsync(memberId: Int, count: Int = 100) {
        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "01553cfb-5f40-4e39-9c92-c904de75070b", fallback = "Geçerli bir üye bilgisi zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("GetOrdersByMemberIdAsync")

            val result = orderRepository.GetOrdersByMemberIdAsync(memberId, count)

            _state.update {
                it.copy(
                    IsLoading = false,
                    Orders = result.Data ?: emptyList(),
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun GetOrderStoresAsync(orderKey: String) {
        if (orderKey.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "eac7e139-74e8-4af8-8da1-ec49ec2a58f5", fallback = "Sipariş anahtarı zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("GetOrderStoresAsync")

            val result = orderRepository.GetOrderStoresAsync(orderKey)

            _state.update {
                it.copy(
                    IsLoading = false,
                    OrderStores = result.Data ?: emptyList(),
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun GetOrderTrackingAsync(cargoTrackingNumber: Int, memberId: Int) {
        if (cargoTrackingNumber <= 0) {
            SetError(BBLocalization.Current.Get(key = "edc6e8b5-1ee0-4712-874f-ce31c25d3d82", fallback = "Geçerli bir kargo takip numarası zorunludur."))
            return
        }

        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "01553cfb-5f40-4e39-9c92-c904de75070b", fallback = "Geçerli bir üye bilgisi zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("GetOrderTrackingAsync")

            val result = orderRepository.GetOrderTrackingAsync(cargoTrackingNumber, memberId)

            _state.update {
                it.copy(
                    IsLoading = false,
                    OrderTracking = result.Data,
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun GetOrderStoreContractAsync(orderKey: String, storeKey: String) {
        if (orderKey.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "eac7e139-74e8-4af8-8da1-ec49ec2a58f5", fallback = "Sipariş anahtarı zorunludur."))
            return
        }

        if (storeKey.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "26417db0-aa8d-46bf-9cb5-4393a8718a66", fallback = "Mağaza anahtarı zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("GetOrderStoreContractAsync")

            val result = contractRepository.GetOrderStoreContractAsync(orderKey, storeKey)

            _state.update {
                it.copy(
                    IsLoading = false,
                    Contract = result.Data,
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun GetOrderCancelationTypes(count: Int = 15) {
        if (count <= 0) {
            SetError(BBLocalization.Current.Get(key = "f59f776b-fc40-4fb1-8930-77e44b3ca307", fallback = "Listeleme adedi sıfırdan büyük olmalıdır."))
            return
        }

        viewModelScope.launch {
            StartLoading("GetOrderCancelationTypes")

            val result = orderRepository.GetOrderCancelationTypes(count)

            _state.update {
                it.copy(
                    IsLoading = false,
                    CancelationTypes = result.Data ?: emptyList(),
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun InsertOrderCancelationAsync(languageId: Int, memberId: Int, insertModel: OrderCancelationInsertModel) {
        if (languageId <= 0) {
            SetError(BBLocalization.Current.Get(key = "bf71e6f0-be25-466c-94af-03f5a128c948", fallback = "Geçerli bir dil bilgisi zorunludur."))
            return
        }

        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "01553cfb-5f40-4e39-9c92-c904de75070b", fallback = "Geçerli bir üye bilgisi zorunludur."))
            return
        }

        if (insertModel.OrderStoreLineId <= 0) {
            SetError(BBLocalization.Current.Get(key = "fb32d16c-15f9-4650-a187-bf4275a48820", fallback = "Sipariş satırı bilgisi zorunludur."))
            return
        }

        if (insertModel.OrderCancelationTypeId <= 0) {
            SetError(BBLocalization.Current.Get(key = "baa151dc-84e6-46b8-89b7-2b5d03b22d1c", fallback = "Sipariş iptal nedeni zorunludur."))
            return
        }

        if (insertModel.OrderKey.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "eac7e139-74e8-4af8-8da1-ec49ec2a58f5", fallback = "Sipariş anahtarı zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("InsertOrderCancelationAsync")

            val result = orderRepository.InsertOrderCancelationAsync(languageId, memberId, insertModel)

            _state.update {
                it.copy(
                    IsLoading = false,
                    IsCancelationCompleted = result.Success,
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun GetReturnRequestReasonsAsync() {
        viewModelScope.launch {
            StartLoading("GetReturnRequestReasonsAsync")

            val result = returnRequestReasonRepository.GetSystemDescReturnRequestReasonsAsync(100)

            _state.update {
                it.copy(
                    IsLoading = false,
                    ReturnRequestReasons = result.Data ?: emptyList(),
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun InsertReturnRequestAsync(languageId: Int, memberId: Int, returnRequest: ReturnRequestDTO) {
        if (languageId <= 0) {
            SetError(BBLocalization.Current.Get(key = "bf71e6f0-be25-466c-94af-03f5a128c948", fallback = "Geçerli bir dil bilgisi zorunludur."))
            return
        }

        if (memberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "01553cfb-5f40-4e39-9c92-c904de75070b", fallback = "Geçerli bir üye bilgisi zorunludur."))
            return
        }

        if (returnRequest.OrderStoreLineId <= 0) {
            SetError(BBLocalization.Current.Get(key = "fb32d16c-15f9-4650-a187-bf4275a48820", fallback = "Sipariş satırı bilgisi zorunludur."))
            return
        }

        if (returnRequest.ReturnRequestReasonId <= 0) {
            SetError(BBLocalization.Current.Get(key = "64d00b3b-3243-4fe4-820a-ef42ca837d51", fallback = "İade nedeni zorunludur."))
            return
        }

        if (returnRequest.Description.isNullOrBlank()) {
            SetError(BBLocalization.Current.Get(key = "6748f42b-352e-4787-83c5-b4797e76a350", fallback = "İade açıklaması zorunludur."))
            return
        }

        viewModelScope.launch {
            StartLoading("InsertReturnRequestAsync")

            val result = returnRequestRepository.InsertReturnRequestAsync(languageId, memberId, returnRequest)

            _state.update {
                it.copy(
                    IsLoading = false,
                    IsReturnRequestCompleted = result.Success,
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun ClearOrders() {
        _state.update {
            it.copy(
                Orders = emptyList()
            )
        }
    }

    fun ClearOrderStores() {
        _state.update {
            it.copy(
                OrderStores = emptyList()
            )
        }
    }

    fun ClearOrderTracking() {
        _state.update {
            it.copy(
                OrderTracking = null
            )
        }
    }

    fun ClearContract() {
        _state.update {
            it.copy(
                Contract = null
            )
        }
    }

    fun ClearError() {
        _state.update {
            it.copy(
                ErrorMessage = null
            )
        }
    }

    fun ResetCancelationResult() {
        _state.update {
            it.copy(
                IsCancelationCompleted = false
            )
        }
    }

    fun ResetReturnRequestResult() {
        _state.update {
            it.copy(
                IsReturnRequestCompleted = false
            )
        }
    }

    fun InsertReviewAsync(insertModel: ReviewInsertModel) {
        if (insertModel.MemberId <= 0) {
            SetError(BBLocalization.Current.Get(key = "01553cfb-5f40-4e39-9c92-c904de75070b", fallback = "Geçerli bir üye bilgisi zorunludur."))
            return
        }

        if (insertModel.ItemId <= 0) {
            SetError(BBLocalization.Current.Get(key = "20db14d0-bc6f-499a-8120-6b54e67e1916", fallback = "Geçerli bir ürün bilgisi zorunludur."))
            return
        }

        if (insertModel.SecureKey.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "0876bc24-422c-4e89-975b-52517af34cde", fallback = "Ürün anahtarı zorunludur."))
            return
        }

        if (insertModel.Content.isBlank()) {
            SetError(BBLocalization.Current.Get(key = "2094a6b9-64b4-4741-b4ab-ccbe1f7c4e45", fallback = "Değerlendirme metni zorunludur."))
            return
        }

        if (insertModel.Rating < 1.0 || insertModel.Rating > 5.0) {
            SetError(BBLocalization.Current.Get(key = "acaf714d-1b73-4b73-b88f-b17c20892d72", fallback = "Değerlendirme puanı 1 ile 5 arasında olmalıdır."))
            return
        }

        viewModelScope.launch {
            StartLoading("InsertReviewAsync")

            val result = reviewRepository.InsertAsync(insertModel)

            _state.update {
                it.copy(
                    IsLoading = false,
                    IsReviewCompleted = result.Success,
                    ErrorMessage = if (result.Success) null else result.Message
                )
            }
        }
    }

    fun ResetReviewResult() {
        _state.update {
            it.copy(
                IsReviewCompleted = false
            )
        }
    }

    private fun StartLoading(action: String) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = action,
                ErrorMessage = null
            )
        }
    }

    private fun SetError(message: String) {
        _state.update {
            it.copy(
                IsLoading = false,
                ErrorMessage = message
            )
        }
    }
}