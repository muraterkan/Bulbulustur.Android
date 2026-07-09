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
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ProductFavoriteInsertModel
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAlarmListInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedStoreInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.ChangePasswordModel
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.DTO.MemberPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberPhoneRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPhoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.DTO.ProductCustomerQuestionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IProductCustomerQuestionRepository
import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.bulbulustur.android.businesslayer.Core.DTO.StoreRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IStoreRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.ChangeMailModel
import com.bulbulustur.android.businesslayer.Core.DTO.ReviewDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReviewRepository
import com.bulbulustur.android.businesslayer.Core.DTO.MemberSubscriptionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberSubscriptionRepository
import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel

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
    val ProductFavoriteMoveToBasketResult: Result<Unit>? = null,
    val ContactPreferenceResult: Result<Unit>? = null,
    val ChangeMailResult: Result<ChangeMailModel>? = null,
    val IsChangeMailRequestSent: Boolean = false,
    val ChangeMailMessage: String? = null,
    val PasswordChangeResult: Result<Unit>? = null,
    val IsPasswordChanged: Boolean = false,
    val PasswordChangeMessage: String? = null,
    val ErrorMessage: String? = null,
    val PhoneListResult: Result<List<MemberPhoneDTO>>? = null,
    val PhoneDetailResult: Result<MemberPhoneDTO?>? = null,
    val PhoneInsertResult: Result<Int>? = null,
    val PhoneDeleteResult: Result<Unit>? = null,
    val PhoneSmsResult: Result<Unit>? = null,
    val PhoneVerifyResult: Result<Unit>? = null,
    val IsPhoneInserted: Boolean = false,
    val IsPhoneVerified: Boolean = false,
    val PhoneMessage: String? = null,
    val ProductFavoriteDeleteResult: Result<Unit>? = null,
    val WholesaleFavoriteDeleteResult: Result<Unit>? = null,
    val ProductCustomerQuestionListResult: Result<List<ProductCustomerQuestionDTO>>? = null,
    val ReturnRequestListResult: Result<List<ReturnRequestDTO>>? = null,
    val ReturnRequestDetailResult: Result<ReturnRequestDTO?>? = null,
    val ReviewListResult: Result<List<ReviewDTO>>? = null,
    val MemberSubscriptionListResult: Result<List<MemberSubscriptionDTO>>? = null,
    val MemberSubscriptionDetailResult: Result<MemberSubscriptionDTO?>? = null,
    val CompanyResult: Result<CompanyDTO?>? = null,
    val CompanyUpdateResult: Result<Any?>? = null,
    val StoreRequestResult: Result<StoreRequestDTO?>? = null,
) {
    val Company: CompanyDTO?
        get() = CompanyResult?.Data
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

    val Phones: List<MemberPhoneDTO>
        get() = PhoneListResult?.Data.orEmpty()

    val ProductCustomerQuestions: List<ProductCustomerQuestionDTO>
        get() = ProductCustomerQuestionListResult?.Data.orEmpty()

    val ReturnRequests: List<ReturnRequestDTO>
        get() = ReturnRequestListResult?.Data.orEmpty()

    val ReturnRequest: ReturnRequestDTO?
        get() = ReturnRequestDetailResult?.Data

    val Reviews: List<ReviewDTO>
        get() = ReviewListResult?.Data.orEmpty()

    val MemberSubscriptions: List<MemberSubscriptionDTO>
        get() = MemberSubscriptionListResult?.Data.orEmpty()

    val MemberSubscription: MemberSubscriptionDTO?
        get() = MemberSubscriptionDetailResult?.Data

    val StoreRequest: StoreRequestDTO?
        get() = StoreRequestResult?.Data
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
    private val wholesaleFavoriteRepository: IWholesaleFavoriteRepository,
    private val memberPhoneRepository: IMemberPhoneRepository,
    private val productCustomerQuestionRepository: IProductCustomerQuestionRepository,
    private val returnRequestRepository: IReturnRequestRepository,
    private val reviewRepository: IReviewRepository,
    private val memberSubscriptionRepository: IMemberSubscriptionRepository,
    private val companyRepository: ICompanyRepository,
    private val storeRequestRepository: IStoreRequestRepository,

) : BaseController() {

    private val _state = MutableStateFlow(AccountControllerState())
    val State: StateFlow<AccountControllerState> = _state.asStateFlow()

    fun GetAccountCompany(languageId: Int, memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetAccountCompany")

            val response = executeService.GetAsync(cacheKey = "") {
                companyRepository.GetAccountCompanyAsync(languageId, memberId)
            }

            Complete {
                copy(
                    CompanyResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun UpdateAccountCompany(languageId: Int, memberId: Int, updateModel: CompanyUpdateModel) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(updateModel.CompanyId, "Şirket bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("UpdateAccountCompany")

            val response = executeService.PostAsync {
                companyRepository.UpdateAccountCompanyAsync(memberId, updateModel)
            }

            Complete {
                copy(
                    CompanyUpdateResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                GetAccountCompany(languageId = languageId, memberId = memberId)
            }
        }
    }

    fun ResetCompanyUpdateResult() {
        _state.update {
            it.copy(
                CompanyUpdateResult = null,
                ErrorMessage = null
            )
        }
    }

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

    fun SendEmailChangingRequest(model: ChangeMailModel) {
        if (!ValidateMember(model.MemberId)) return

        val newEmail = model.NewEmail.trim()
        val reNewEmail = model.ReNewEmail.trim()

        if (newEmail.isBlank() || reNewEmail.isBlank()) {
            SetError("Yeni e-posta adreslerini giriniz.")
            return
        }

        if (!newEmail.equals(reNewEmail, ignoreCase = true)) {
            SetError("Yeni e-posta adresleri eşleşmiyor.")
            return
        }

        if (model.Email.trim().equals(newEmail, ignoreCase = true)) {
            SetError("Yeni e-posta adresi mevcut e-posta adresinizle aynı olamaz.")
            return
        }

        val requestModel = model.copy(
            Email = model.Email.trim(),
            NewEmail = newEmail,
            ReNewEmail = reNewEmail
        )

        viewModelScope.launch {
            SetLoading("SendEmailChangingRequest")

            val response = executeService.PostAsync(operationType = "Account.Email.ChangeRequest") {
                memberRepository.SendEmailChangingRequestAsync(requestModel)
            }

            Complete {
                copy(
                    ChangeMailResult = response,
                    IsChangeMailRequestSent = response.Success,
                    ChangeMailMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun ChangePassword(languageId: Int, model: ChangePasswordModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(model.MemberId)) return

        if (model.ActivePassword.isBlank()) {
            SetError("Mevcut şifrenizi giriniz.")
            return
        }

        if (model.NewPassword.length !in 8..16) {
            SetError("Yeni şifreniz 8 ile 16 karakter arasında olmalıdır.")
            return
        }

        if (model.NewPassword != model.ReNewPassword) {
            SetError("Yeni şifreler birbiriyle eşleşmiyor.")
            return
        }

        viewModelScope.launch {
            SetLoading("ChangePassword")

            val response = executeService.PostAsync(operationType = "Account.Password.Change") {
                memberRepository.ChangePasswordAsync(languageId, model)
            }

            Complete {
                copy(
                    PasswordChangeResult = response,
                    IsPasswordChanged = response.Success,
                    PasswordChangeMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun ResetPasswordChangeState() {
        _state.update {
            it.copy(
                PasswordChangeResult = null,
                IsPasswordChanged = false,
                PasswordChangeMessage = null,
                ErrorMessage = null
            )
        }
    }

    fun ResetChangeMailState() {
        _state.update {
            it.copy(
                ChangeMailResult = null,
                IsChangeMailRequestSent = false,
                ChangeMailMessage = null,
                ErrorMessage = null
            )
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

    fun GetPhones(languageId: Int, memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetPhones")

            val response = executeService.GetAsync(cacheKey = "") {
                memberPhoneRepository.GetMemberPhonesAsync(
                    languageId = languageId,
                    memberId = memberId,
                    count = count
                )
            }

            Complete {
                copy(
                    PhoneListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetPhone(languageId: Int, memberPhoneId: Int, memberId: Int) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberPhoneId, "Telefon kaydı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("GetPhone")

            val response = executeService.GetAsync(cacheKey = "") {
                memberPhoneRepository.GetMemberPhoneByIdExtendedAsync(
                    languageId = languageId,
                    memberPhoneId = memberPhoneId,
                    memberId = memberId
                )
            }

            Complete {
                copy(
                    PhoneDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertPhone(
        languageId: Int,
        memberId: Int,
        phone: String,
        onSuccess: ((Int) -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return

        val normalizedPhone = phone.trim()

        if (normalizedPhone.isBlank()) {
            SetError("Telefon numarası giriniz.")
            return
        }

        val model = MemberPhoneInsertModel(
            InsertedBy = memberId,
            Phone = normalizedPhone
        )

        viewModelScope.launch {
            SetLoading("InsertPhone")

            val response = executeService.PostAsync(operationType = "Account.Phone.Insert") {
                memberPhoneRepository.InsertAsync(
                    languageId = languageId,
                    model = model
                )
            }

            Complete {
                copy(
                    PhoneInsertResult = response,
                    IsPhoneInserted = response.Success,
                    PhoneMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            val memberPhoneId = response.Data ?: 0

            if (response.Success && memberPhoneId > 0) {
                onSuccess?.invoke(memberPhoneId)
            }
        }
    }

    fun DeletePhone(
        languageId: Int,
        memberId: Int,
        memberPhoneId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberPhoneId, "Telefon kaydı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeletePhone")

            val response = executeService.PostAsync(operationType = "Account.Phone.Delete") {
                memberPhoneRepository.DeleteAsync(
                    languageId = languageId,
                    phoneId = memberPhoneId,
                    memberId = memberId
                )
            }

            Complete {
                copy(
                    PhoneDeleteResult = response,
                    PhoneMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun SendPhoneVerificationSms(
        languageId: Int,
        memberId: Int,
        memberPhoneId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberPhoneId, "Telefon kaydı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("SendPhoneVerificationSms")

            val response = executeService.GetAsync(cacheKey = "") {
                memberPhoneRepository.SendVerificationSmsAsync(
                    languageId = languageId,
                    memberPhoneId = memberPhoneId,
                    memberId = memberId
                )
            }

            Complete {
                copy(
                    PhoneSmsResult = response,
                    PhoneMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun VerifyPhone(
        languageId: Int,
        memberId: Int,
        memberPhoneId: Int,
        verificationCode: String,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberPhoneId, "Telefon kaydı bulunamadı.")) return

        val normalizedCode = verificationCode.trim()

        if (normalizedCode.length != 4 || normalizedCode.any { !it.isDigit() }) {
            SetError("4 haneli doğrulama kodunu giriniz.")
            return
        }

        val model = MemberPhoneUpdateModel(
            MemberPhoneId = memberPhoneId,
            InsertedBy = memberId,
            VerificationCode = normalizedCode
        )

        viewModelScope.launch {
            SetLoading("VerifyPhone")

            val response = executeService.PostAsync(operationType = "Account.Phone.Verify") {
                memberPhoneRepository.VerifyPhoneAsync(
                    languageId = languageId,
                    model = model
                )
            }

            Complete {
                copy(
                    PhoneVerifyResult = response,
                    IsPhoneVerified = response.Success,
                    PhoneMessage = response.Message.takeIf { response.Success },
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) onSuccess?.invoke()
        }
    }

    fun ResetPhoneState() {
        _state.update {
            it.copy(
                PhoneDetailResult = null,
                PhoneInsertResult = null,
                PhoneDeleteResult = null,
                PhoneSmsResult = null,
                PhoneVerifyResult = null,
                IsPhoneInserted = false,
                IsPhoneVerified = false,
                PhoneMessage = null,
                ErrorMessage = null
            )
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

    fun GetReturnRequests(languageId: Int, memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetReturnRequests")

            val response = executeService.GetAsync(cacheKey = "") {
                returnRequestRepository.GetReturnRequestsAsync(languageId, memberId, count)
            }

            Complete {
                copy(
                    ReturnRequestListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetMemberReviews(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMemberReviews")

            val response = executeService.GetAsync(cacheKey = "") {
                reviewRepository.GetMemberReviewsAsync(memberId, count)
            }

            Complete {
                copy(
                    ReviewListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }



    fun GetReturnRequest(languageId: Int, memberId: Int, returnRequestId: Int) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(returnRequestId, "İade talebi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("GetReturnRequest")

            val response = executeService.GetAsync(cacheKey = "") {
                returnRequestRepository.GetReturnRequestByIdExtendedAsync(languageId, memberId, returnRequestId)
            }

            Complete {
                copy(
                    ReturnRequestDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun ResetReturnRequestDetail() {
        _state.update {
            it.copy(
                ReturnRequestDetailResult = null,
                ErrorMessage = null
            )
        }
    }

    fun GetMemberSubscriptions(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMemberSubscriptions")

            val response = executeService.GetAsync(cacheKey = "") {
                memberSubscriptionRepository.GetAccountSubscriptionsAsync(memberId, count)
            }

            Complete {
                copy(
                    MemberSubscriptionListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetMemberSubscription(memberId: Int, memberSubscriptionId: Int) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberSubscriptionId, "Abonelik bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("GetMemberSubscription")

            val response = executeService.GetAsync(cacheKey = "") {
                memberSubscriptionRepository.GetAccountSubscriptionByIdExtendedAsync(memberId, memberSubscriptionId)
            }

            Complete {
                copy(
                    MemberSubscriptionDetailResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun ResetMemberSubscriptionDetail() {
        _state.update {
            it.copy(
                MemberSubscriptionDetailResult = null,
                ErrorMessage = null
            )
        }
    }

    fun GetMemberProductCustomerQuestions(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMemberProductCustomerQuestions")

            val response = executeService.GetAsync(cacheKey = "") {
                productCustomerQuestionRepository.GetMemberProductCustomerQuestionsAsync(memberId, count)
            }

            Complete {
                copy(
                    ProductCustomerQuestionListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetFavorites(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetFavorites")

            val productFavoritesDeferred = async {
                executeService.GetAsync(cacheKey = "") {
                    productFavoriteRepository.GetProductFavoritesAsync(memberId, count)
                }
            }

            val wholesaleFavoritesDeferred = async {
                executeService.GetAsync(cacheKey = "") {
                    wholesaleFavoriteRepository.GetWholesaleFavoriteListAsync(memberId, count)
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

    fun GetProductFavorites(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetProductFavorites")

            val response = executeService.GetAsync(cacheKey = "") {
                productFavoriteRepository.GetProductFavoritesAsync(memberId, count)
            }

            Complete {
                copy(
                    ProductFavoriteListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetWholesaleFavorites(memberId: Int, count: Int = 100) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetWholesaleFavorites")

            val response = executeService.GetAsync(cacheKey = "") {
                wholesaleFavoriteRepository.GetWholesaleFavoriteListAsync(memberId, count)
            }

            Complete {
                copy(
                    WholesaleFavoriteListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertProductFavorite(memberId: Int, model: ProductFavoriteInsertModel, onSuccess: (() -> Unit)? = null) {
        if (memberId <= 0) {
            SetError("Oturum bilgisi bulunamadı.")
            return
        }

        viewModelScope.launch {
            SetLoading("InsertProductFavorite")

            val result = productFavoriteRepository.InsertProductFavoriteAsync(memberId, model)

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    CurrentAction = "InsertProductFavorite",
                    ErrorMessage = result.Message.takeIf { !result.Success }
                )
            }

            if (result.Success) {
                onSuccess?.invoke()
            }
        }
    }

    fun DeleteProductFavorite(
        memberId: Int,
        favoriteId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(favoriteId, "Favori ürün bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteProductFavorite")

            val response = executeService.PostAsync(operationType = "Account.ProductFavorite.Delete") {
                productFavoriteRepository.DeleteProductFavoriteAsync(memberId, favoriteId)
            }

            Complete {
                copy(
                    ProductFavoriteDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                onSuccess?.invoke()
            }
        }
    }

    fun MoveProductFavoriteToBasket(
        memberId: Int,
        favoriteId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(favoriteId, "Favori ürün bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("MoveProductFavoriteToBasket")

            val response = executeService.PostAsync(
                operationType = "Account.ProductFavorite.MoveToBasket"
            ) {
                productFavoriteRepository.MoveProductFavoriteToBasketAsync(
                    memberId = memberId,
                    favoriteId = favoriteId
                )
            }

            Complete {
                copy(
                    ProductFavoriteMoveToBasketResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                onSuccess?.invoke()
            }
        }
    }

    fun GetAccountStoreRequestStatus(memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetAccountStoreRequestStatus")

            val response = executeService.GetAsync(cacheKey = "") {
                storeRequestRepository.GetAccountStoreRequestStatusAsync(memberId)
            }

            Complete {
                copy(
                    StoreRequestResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun DeleteWholesaleFavorite(
        memberId: Int,
        wholesaleFavoriteId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(wholesaleFavoriteId, "Toptan favori ürün bilgisi bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteWholesaleFavorite")

            val response = executeService.PostAsync(operationType = "Account.WholesaleFavorite.Delete") {
                wholesaleFavoriteRepository.DeleteAsync(memberId, wholesaleFavoriteId)
            }

            Complete {
                copy(
                    WholesaleFavoriteDeleteResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                onSuccess?.invoke()
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