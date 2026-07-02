package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAgreementDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberAlarmListDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberBankAccountDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberCouponDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedCompanyDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedStoreDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberLoginActivityDTO
import com.bulbulustur.android.businesslayer.Core.DTO.ProductFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleFavoriteDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAgreementRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAlarmListRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberCouponRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedCompanyRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedStoreRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLoginActivityRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IProductFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedStoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AccountControllerState(
    val IsLoading: Boolean = false,
    val IsContactPreferenceSaving: Boolean = false,
    val IsContactPreferenceSaved: Boolean = false,
    val CurrentAction: String? = null,
    val MemberResult: Result<MemberDTO?>? = null,
    val MemberUpdateResult: Result<MemberUpdateModel?>? = null,
    val AddressListResult: Result<List<MemberAddressDTO>>? = null,
    val AddressDetailResult: Result<MemberAddressUpdateModel?>? = null,
    val AddressInsertResult: Result<Unit>? = null,
    val AddressUpdateResult: Result<Unit>? = null,
    val AddressDeleteResult: Result<Unit>? = null,
    val BankAccountListResult: Result<List<MemberBankAccountDTO>>? = null,
    val BankAccountDetailResult: Result<MemberBankAccountUpdateModel?>? = null,
    val BankAccountInsertResult: Result<Unit>? = null,
    val BankAccountUpdateResult: Result<Unit>? = null,
    val BankAccountDeleteResult: Result<Unit>? = null,
    val AlarmListResult: Result<List<MemberAlarmListDTO>>? = null,
    val AlarmInsertResult: Result<Unit>? = null,
    val AlarmDeleteResult: Result<Unit>? = null,
    val FollowedCompanyListResult: Result<List<MemberFollowedCompanyDTO>>? = null,
    val FollowedCompanyInsertResult: Result<Unit>? = null,
    val FollowedCompanyDeleteResult: Result<Unit>? = null,
    val FollowedStoreListResult: Result<List<MemberFollowedStoreDTO>>? = null,
    val FollowedStoreInsertResult: Result<Unit>? = null,
    val FollowedStoreDeleteResult: Result<Unit>? = null,
    val AgreementResult: Result<MemberAgreementDTO?>? = null,
    val LoginActivityListResult: Result<List<MemberLoginActivityDTO>>? = null,
    val CouponListResult: Result<List<MemberCouponDTO>>? = null,
    val ProductFavoriteListResult: Result<List<ProductFavoriteDTO>>? = null,
    val WholesaleFavoriteListResult: Result<List<WholesaleFavoriteDTO>>? = null,
    val ContactPreferenceResult: Result<Unit>? = null,
    val ErrorMessage: String? = null
) {
    val Member: MemberDTO?
        get() = MemberResult?.Data

    val Addresses: List<MemberAddressDTO>
        get() = AddressListResult?.Data.orEmpty()

    val Address: MemberAddressUpdateModel?
        get() = AddressDetailResult?.Data

    val BankAccounts: List<MemberBankAccountDTO>
        get() = BankAccountListResult?.Data.orEmpty()

    val BankAccount: MemberBankAccountUpdateModel?
        get() = BankAccountDetailResult?.Data

    val Alarms: List<MemberAlarmListDTO>
        get() = AlarmListResult?.Data.orEmpty()

    val FollowedCompanies: List<MemberFollowedCompanyDTO>
        get() = FollowedCompanyListResult?.Data.orEmpty()

    val FollowedStores: List<MemberFollowedStoreDTO>
        get() = FollowedStoreListResult?.Data.orEmpty()

    val LoginActivities: List<MemberLoginActivityDTO>
        get() = LoginActivityListResult?.Data.orEmpty()

    val Coupons: List<MemberCouponDTO>
        get() = CouponListResult?.Data.orEmpty()

    val ProductFavorites: List<ProductFavoriteDTO>
        get() = ProductFavoriteListResult?.Data.orEmpty()

    val WholesaleFavorites: List<WholesaleFavoriteDTO>
        get() = WholesaleFavoriteListResult?.Data.orEmpty()
}

class AccountController(
    private val executeService: IExecuteService,
    private val memberRepository: IMemberRepository,
    private val memberAddressRepository: IMemberAddressRepository,
    private val memberBankAccountRepository: IMemberBankAccountRepository,
    private val memberAlarmListRepository: IMemberAlarmListRepository,
    private val memberFollowedCompanyRepository: IMemberFollowedCompanyRepository,
    private val memberFollowedStoreRepository: IMemberFollowedStoreRepository,
    private val memberAgreementRepository: IMemberAgreementRepository,
    private val memberLoginActivityRepository: IMemberLoginActivityRepository,
    private val memberCouponRepository: IMemberCouponRepository,
    private val productFavoriteRepository: IProductFavoriteRepository,
    private val wholesaleFavoriteRepository: IWholesaleFavoriteRepository
) : BaseController() {

    private val _state = MutableStateFlow(AccountControllerState())
    val State: StateFlow<AccountControllerState> = _state.asStateFlow()

    fun GetAccount(languageId: Int, memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetAccount")

            val response = executeService.GetAsync(cacheKey = "") {
                memberRepository.GetMemberByIdExtendedAsync(languageId, memberId)
            }

            Complete {
                copy(
                    MemberResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetMember(languageId: Int, memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMember")

            val response = executeService.GetAsync(cacheKey = "") {
                memberRepository.GetMemberByIdAsync(languageId, memberId)
            }

            Complete {
                copy(
                    MemberUpdateResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun UpdateMember(model: MemberUpdateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(model.MemberId)) return

        viewModelScope.launch {
            SetLoading("UpdateMember")

            val response = executeService.PostAsync(operationType = "Account.Member.Update") {
                memberRepository.UpdateAsync(model)
            }

            Complete {
                copy(ErrorMessage = response.Message.takeIf { !response.Success })
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun SetContactPreference(
        memberId: Int,
        emailPreference: Int,
        smsPreference: Int,
        phonePreference: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (_state.value.IsContactPreferenceSaving) return

        val model = MemberUpdateModel(
            MemberId = memberId,
            ContactPreferenceEmail = emailPreference,
            ContactPreferenceSms = smsPreference,
            ContactPreferencePhone = phonePreference
        )

        viewModelScope.launch {
            _state.update {
                it.copy(
                    IsContactPreferenceSaving = true,
                    IsContactPreferenceSaved = false,
                    CurrentAction = "SetContactPreference",
                    ContactPreferenceResult = null,
                    ErrorMessage = null
                )
            }

            val response = executeService.PostAsync(operationType = "Account.ContactPreference.Update") {
                memberRepository.SetContactPreferenceAsync(model)
            }

            _state.update {
                it.copy(
                    IsContactPreferenceSaving = false,
                    IsContactPreferenceSaved = response.Success,
                    ContactPreferenceResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetAddresses(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetAddresses")

            val response = executeService.GetAsync(cacheKey = "") {
                memberAddressRepository.GetAccountAddressesAsync(memberId, count)
            }

            Complete {
                copy(
                    AddressListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetAddress(memberId: Int, addressKey: String) {
        if (!ValidateMember(memberId)) return

        if (addressKey.isBlank()) {
            SetError("Adres anahtarı bulunamadı.")
            return
        }

        viewModelScope.launch {
            SetLoading("GetAddress")

            val response = executeService.GetAsync(cacheKey = "") {
                memberAddressRepository.GetAccountAddressByIdAsync(memberId, addressKey)
            }

            Complete {
                copy(
                    AddressDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertAddress(memberId: Int, model: MemberAddressInsertModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("InsertAddress")

            val response = executeService.PostAsync(operationType = "Account.Address.Insert") {
                memberAddressRepository.InsertAccountAddressAsync(memberId, model)
            }

            Complete {
                copy(
                    AddressInsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun UpdateAddress(memberId: Int, model: MemberAddressUpdateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("UpdateAddress")

            val response = executeService.PostAsync(operationType = "Account.Address.Update") {
                memberAddressRepository.UpdateAccountAddressAsync(memberId, model)
            }

            Complete {
                copy(
                    AddressUpdateResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun DeleteAddress(memberId: Int, addressId: Int, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(addressId, "Adres bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteAddress")

            val response = executeService.PostAsync(operationType = "Account.Address.Delete") {
                memberAddressRepository.DeleteAccountAddressAsync(memberId, addressId)
            }

            Complete {
                copy(
                    AddressDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetBankAccounts(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetBankAccounts")

            val response = executeService.GetAsync(cacheKey = "") {
                memberBankAccountRepository.GetAccountBankAccountsAsync(memberId, count)
            }

            Complete {
                copy(
                    BankAccountListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetBankAccount(memberId: Int, bankAccountId: Int) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(bankAccountId, "Banka hesabı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("GetBankAccount")

            val response = executeService.GetAsync(cacheKey = "") {
                memberBankAccountRepository.GetAccountBankAccountByIdAsync(memberId, bankAccountId)
            }

            Complete {
                copy(
                    BankAccountDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertBankAccount(memberId: Int, model: MemberBankAccountInsertModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("InsertBankAccount")

            val response = executeService.PostAsync(operationType = "Account.BankAccount.Insert") {
                memberBankAccountRepository.InsertAccountBankAccountAsync(memberId, model)
            }

            Complete {
                copy(
                    BankAccountInsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun UpdateBankAccount(memberId: Int, model: MemberBankAccountUpdateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("UpdateBankAccount")

            val response = executeService.PostAsync(operationType = "Account.BankAccount.Update") {
                memberBankAccountRepository.UpdateAccountBankAccountAsync(memberId, model)
            }

            Complete {
                copy(
                    BankAccountUpdateResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun DeleteBankAccount(memberId: Int, bankAccountId: Int, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(bankAccountId, "Banka hesabı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteBankAccount")

            val response = executeService.PostAsync(operationType = "Account.BankAccount.Delete") {
                memberBankAccountRepository.DeleteAccountBankAccountAsync(memberId, bankAccountId)
            }

            Complete {
                copy(
                    BankAccountDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetAlarms(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetAlarms")

            val response = executeService.GetAsync(cacheKey = "") {
                memberAlarmListRepository.GetAccountAlarmLists(memberId, count)
            }

            Complete {
                copy(
                    AlarmListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertAlarm(memberId: Int, model: MemberAlarmListInsertModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("InsertAlarm")

            val response = executeService.PostAsync(operationType = "Account.Alarm.Insert") {
                memberAlarmListRepository.InsertAccountAlarmAsync(memberId, model)
            }

            Complete {
                copy(
                    AlarmInsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun DeleteAlarm(memberId: Int, memberAlarmListId: Int, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberAlarmListId, "Alarm bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteAlarm")

            val response = executeService.PostAsync(operationType = "Account.Alarm.Delete") {
                memberAlarmListRepository.DeleteAccountAlarmAsync(memberId, memberAlarmListId)
            }

            Complete {
                copy(
                    AlarmDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetFollowedCompanies(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetFollowedCompanies")

            val response = executeService.GetAsync(cacheKey = "") {
                memberFollowedCompanyRepository.GetAccountFollowedCompanies(memberId, count)
            }

            Complete {
                copy(
                    FollowedCompanyListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertFollowedCompany(memberId: Int, model: MemberFollowedCompanyInsertModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("InsertFollowedCompany")

            val response = executeService.PostAsync(operationType = "Account.FollowedCompany.Insert") {
                memberFollowedCompanyRepository.InsertAccountFollowedCompany(memberId, model)
            }

            Complete {
                copy(
                    FollowedCompanyInsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun DeleteFollowedCompany(memberId: Int, followedCompanyId: Int, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(followedCompanyId, "Takip edilen şirket bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteFollowedCompany")

            val response = executeService.PostAsync(operationType = "Account.FollowedCompany.Delete") {
                memberFollowedCompanyRepository.DeleteAccountFollowedCompany(memberId, followedCompanyId)
            }

            Complete {
                copy(
                    FollowedCompanyDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetFollowedStores(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetFollowedStores")

            val response = executeService.GetAsync(cacheKey = "") {
                memberFollowedStoreRepository.GetAccountFollowedStores(memberId, count)
            }

            Complete {
                copy(
                    FollowedStoreListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertFollowedStore(memberId: Int, model: MemberFollowedStoreInsertModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("InsertFollowedStore")

            val response = executeService.PostAsync(operationType = "Account.FollowedStore.Insert") {
                memberFollowedStoreRepository.InsertAccountFollowedStoreAsync(memberId, model)
            }

            Complete {
                copy(
                    FollowedStoreInsertResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun DeleteFollowedStore(memberId: Int, followedStoreId: Int, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(followedStoreId, "Takip edilen mağaza bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteFollowedStore")

            val response = executeService.PostAsync(operationType = "Account.FollowedStore.Delete") {
                memberFollowedStoreRepository.DeleteAccountFollowedStoreAsync(memberId, followedStoreId)
            }

            Complete {
                copy(
                    FollowedStoreDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun GetLatestAgreement(memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetLatestAgreement")

            val response = executeService.GetAsync(cacheKey = "") {
                memberAgreementRepository.GetLatestAccountAgreementAsync(memberId)
            }

            Complete {
                copy(
                    AgreementResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetLoginActivities(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetLoginActivities")

            val response = executeService.GetAsync(cacheKey = "") {
                memberLoginActivityRepository.GetAccountLoginActivities(memberId, count)
            }

            Complete {
                copy(
                    LoginActivityListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetCoupons(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetCoupons")

            val response = executeService.GetAsync(cacheKey = "") {
                memberCouponRepository.GetMemberCouponsAsync(memberId, count)
            }

            Complete {
                copy(
                    CouponListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetFavorites() {
        viewModelScope.launch {
            SetLoading("GetFavorites")

            val productFavoritesDeferred = async {
                executeService.GetAsync(cacheKey = "") {
                    productFavoriteRepository.GetProductFavoriteListAsync()
                }
            }

            val wholesaleFavoritesDeferred = async {
                executeService.GetAsync(cacheKey = "") {
                    wholesaleFavoriteRepository.GetWholesaleFavoriteListAsync()
                }
            }

            val productFavorites = productFavoritesDeferred.await()
            val wholesaleFavorites = wholesaleFavoritesDeferred.await()

            val errorMessage = when {
                !productFavorites.Success -> productFavorites.Message
                !wholesaleFavorites.Success -> wholesaleFavorites.Message
                else -> null
            }

            Complete {
                copy(
                    ProductFavoriteListResult = productFavorites,
                    WholesaleFavoriteListResult = wholesaleFavorites,
                    ErrorMessage = errorMessage
                )
            }
        }
    }

    private fun SetLoading(currentAction: String) {
        _state.update {
            it.copy(
                IsLoading = true,
                CurrentAction = currentAction,
                ErrorMessage = null
            )
        }
    }

    private fun Complete(update: AccountControllerState.() -> AccountControllerState) {
        _state.update {
            it.update().copy(
                IsLoading = false
            )
        }
    }

    private fun ValidateMember(memberId: Int): Boolean {
        if (memberId > 0) return true

        SetError("Bu işlem için giriş yapmalısınız.")
        return false
    }

    private fun ValidateId(id: Int, message: String): Boolean {
        if (id > 0) return true

        SetError(message)
        return false
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