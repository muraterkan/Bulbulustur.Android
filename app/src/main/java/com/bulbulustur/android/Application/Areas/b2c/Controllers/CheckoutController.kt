package com.bulbulustur.android.Application.Areas.b2c.Controllers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.CheckoutContractDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.CheckoutContractRequestModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CheckoutControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,

    val AddressListResult: Result<List<MemberAddressDTO>>? = null,
    val AddressDetailResult: Result<MemberAddressUpdateModel?>? = null,
    val AddressInsertResult: Result<Unit>? = null,
    val AddressUpdateResult: Result<Unit>? = null,


    val SelectedDeliveryAddressId: Int = 0,

    val SelectedInvoiceAddressId: Int = 0,

    val ContractResult: Result<CheckoutContractDTO>? = null,
    val IsContractLoading: Boolean = false,
    val ContractErrorMessage: String? = null,


    val ErrorMessage: String? = null
) {
    val Addresses: List<MemberAddressDTO>
        get() = AddressListResult?.Data.orEmpty()

    val AddressDetail: MemberAddressUpdateModel?
        get() = AddressDetailResult?.Data


    val SelectedDeliveryAddress: MemberAddressDTO?
        get() =
            Addresses.firstOrNull {
                it.MemberAddressId == SelectedDeliveryAddressId
            }

    val SelectedInvoiceAddress: MemberAddressDTO?
        get() =
            Addresses.firstOrNull {
                it.MemberAddressId == SelectedInvoiceAddressId
            }

    val PreInformationHtml: String get() = ContractResult?.Data?.PreInformationHtml.orEmpty()

    val DistanceSellingHtml: String get() = ContractResult?.Data?.DistanceSellingHtml.orEmpty()

}

class CheckoutController(
    private val executeService: IExecuteService,
    private val memberAddressRepository: IMemberAddressRepository,
    private val contractRepository: IContractRepository
) : BaseController() {

    private val _state =
        MutableStateFlow(
            CheckoutControllerState()
        )

    val State: StateFlow<CheckoutControllerState> =
        _state.asStateFlow()

    fun LoadAddresses(
        memberId: Int,
        count: Int = 100
    ) {
        if (memberId <= 0) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressListResult = null,
                    SelectedDeliveryAddressId = 0,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "LoadAddresses",
                    ErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = ""
                ) {
                    memberAddressRepository.GetAccountAddressesAsync(
                        memberId = memberId,
                        count = count
                    )
                }

            _state.update { currentState ->
                val addresses =
                    response.Data.orEmpty()

                val selectedAddressId =
                    currentState.SelectedDeliveryAddressId
                        .takeIf { selectedId ->
                            selectedId > 0 &&
                                addresses.any { address ->
                                    address.MemberAddressId ==
                                        selectedId
                                }
                        }
                        ?: addresses
                            .firstOrNull {
                                it.IsDefault
                            }
                            ?.MemberAddressId
                        ?: addresses
                            .firstOrNull()
                            ?.MemberAddressId
                        ?: 0

                currentState.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressListResult = response,
                    SelectedDeliveryAddressId =
                        selectedAddressId,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun LoadAddress(
        memberId: Int,
        addressKey: String
    ) {
        if (
            memberId <= 0 ||
            addressKey.isBlank()
        ) {
            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressDetailResult = null,
                    ErrorMessage = null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "LoadAddress",
                    AddressDetailResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.GetAsync(
                    cacheKey = ""
                ) {
                    memberAddressRepository.GetAccountAddressByIdAsync(
                        memberId = memberId,
                        addressKey = addressKey
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressDetailResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }
        }
    }

    fun InsertAddress(
        memberId: Int,
        model: MemberAddressInsertModel,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "InsertAddress",
                    AddressInsertResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.PostAsync(
                    operationType = "Checkout.Address.Insert"
                ) {
                    memberAddressRepository.InsertAccountAddressAsync(
                        memberId = memberId,
                        model = model
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressInsertResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                LoadAddresses(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateAddress(
        memberId: Int,
        model: MemberAddressUpdateModel,
        onSuccess: (() -> Unit)? = null
    ) {
        if (memberId <= 0) return

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsLoading = true,
                    CurrentAction = "UpdateAddress",
                    AddressUpdateResult = null,
                    ErrorMessage = null
                )
            }

            val response =
                executeService.PostAsync(
                    operationType = "Checkout.Address.Update"
                ) {
                    memberAddressRepository.UpdateAccountAddressAsync(
                        memberId = memberId,
                        model = model
                    )
                }

            _state.update {
                it.copy(
                    IsLoading = false,
                    CurrentAction = null,
                    AddressUpdateResult = response,
                    ErrorMessage =
                        response.Message.takeIf {
                            !response.Success
                        }
                )
            }

            if (response.Success) {
                LoadAddresses(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun ClearAddressDetail() {
        _state.update {
            it.copy(
                AddressDetailResult = null,
                ErrorMessage = null
            )
        }
    }

    fun SelectDeliveryAddress(
        memberAddressId: Int
    ) {
        if (memberAddressId <= 0) return

        _state.update { currentState ->
            if (
                currentState.Addresses.none { address ->
                    address.MemberAddressId ==
                            memberAddressId
                }
            ) {
                currentState
            } else {
                currentState.copy(
                    SelectedDeliveryAddressId =
                        memberAddressId,

                    SelectedInvoiceAddressId =
                        memberAddressId
                )
            }
        }
    }

    fun SelectInvoiceAddress(
        memberAddressId: Int
    ) {
        if (memberAddressId <= 0) return

        _state.update { currentState ->
            if (
                currentState.Addresses.none { address ->
                    address.MemberAddressId ==
                            memberAddressId
                }
            ) {
                currentState
            } else {
                currentState.copy(
                    SelectedInvoiceAddressId =
                        memberAddressId
                )
            }
        }
    }
    fun LoadContracts(memberId: Int, languageId: Int, deliveryAddressId: Int, invoiceAddressId: Int, installmentCount: Int) {
        if (memberId <= 0 || deliveryAddressId <= 0 || invoiceAddressId <= 0) {
            _state.update {
                it.copy(
                    ContractResult = null,
                    IsContractLoading = false,
                    ContractErrorMessage = null
                )
            }
            return
        }

        val safeInstallmentCount = installmentCount.coerceAtLeast(1)

        viewModelScope.launch {
            _state.update { it.copy(IsContractLoading = true, ContractErrorMessage = null) }

            val request = CheckoutContractRequestModel(
                MemberId = 10000002,
                LanguageId = 1,
                DeliveryAddressId = 1,
                InvoiceAddressId = 1,
                InstallmentCount = 1
            )

            Log.d(
                "ContractDebug",
                "FORCED REQUEST MemberId=${request.MemberId} LanguageId=${request.LanguageId} DeliveryAddressId=${request.DeliveryAddressId} InvoiceAddressId=${request.InvoiceAddressId} InstallmentCount=${request.InstallmentCount}"
            )

            val response = executeService.PostAsync(operationType = "Checkout.Contract.Load") {
                contractRepository.GetCheckoutContractsAsync(request)
            }

            Log.d(
                "ContractDebug",
                "RESPONSE Success=${response.Success} PreLength=${response.Data?.PreInformationHtml?.length ?: 0} DistanceLength=${response.Data?.DistanceSellingHtml?.length ?: 0} Message=${response.Message}"
            )

            _state.update {
                it.copy(
                    ContractResult = response,
                    IsContractLoading = false,
                    ContractErrorMessage = response.Message.takeIf { !response.Success })
            }
        }
    }
}
