package com.bulbulustur.android.Application.Controllers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageLevelRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescEducationRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescGenderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBioUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileEducationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileJobTitleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateAddressModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateBirthDateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateGenderModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateProfessionModel
import com.bulbulustur.android.businesslayer.Core.Repository.MemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescLanguageLevelRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescEducationRepository
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfileControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val MemberResult: Result<MemberDTO?>? = null,
    val MemberProfileResult: Result<MemberProfileDTO?>? = null,
    val GenderListResult: Result<List<SystemDescGenderDTO>>? = null,
    val EducationListResult: Result<List<SystemDescEducationDTO>>? = null,
    val MemberLanguageListResult: Result<List<MemberLanguageDTO>>? = null,
    val LanguageListResult: Result<List<SystemDescLanguageDTO>>? = null,
    val LanguageLevelListResult: Result<List<SystemDescLanguageLevelDTO>>? = null,
    val ErrorMessage: String? = null
) {
    val Member: MemberDTO?
        get() = MemberResult?.Data

    val MemberProfile: MemberProfileDTO?
        get() = MemberProfileResult?.Data

    val Genders: List<SystemDescGenderDTO>
        get() = GenderListResult?.Data.orEmpty()

    val Educations: List<SystemDescEducationDTO>
        get() = EducationListResult?.Data.orEmpty()













    val MemberLanguages: List<MemberLanguageDTO>
        get() = MemberLanguageListResult?.Data.orEmpty()

    val Languages: List<SystemDescLanguageDTO>
        get() = LanguageListResult?.Data.orEmpty()

    val LanguageLevels: List<SystemDescLanguageLevelDTO>
        get() = LanguageLevelListResult?.Data.orEmpty()
}

class ProfileController(
    private val executeService: IExecuteService,
    private val memberRepository: IMemberRepository,
    private val systemDescGenderRepository: ISystemDescGenderRepository,
    private val memberProfileRepository: IMemberProfileRepository = MemberProfileRepository(),
    private val systemDescEducationRepository: ISystemDescEducationRepository = SystemDescEducationRepository(),
    private val memberLanguageRepository: IMemberLanguageRepository = MemberLanguageRepository(),
    private val systemDescLanguageRepository: ISystemDescLanguageRepository = SystemDescLanguageRepository(),
    private val systemDescLanguageLevelRepository: ISystemDescLanguageLevelRepository = SystemDescLanguageLevelRepository()
) : BaseController() {

    private val _state = MutableStateFlow(ProfileControllerState())
    val State: StateFlow<ProfileControllerState> = _state.asStateFlow()

    fun GetProfile(languageId: Int, memberId: Int) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetProfile")

            val response = executeService.GetAsync(cacheKey = "") {
                memberRepository.GetMemberByIdExtendedAsync(languageId = languageId, memberId = memberId)
            }

            Complete {
                copy(
                    MemberResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun RefreshProfile(languageId: Int, memberId: Int) {
        GetProfile(languageId = languageId, memberId = memberId)
    }

    fun GetMemberProfile(memberId: Int) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMemberProfile")

            val response = executeService.GetAsync(cacheKey = "") {
                memberProfileRepository.GetMemberProfileByMemberIdAsync(memberId)
            }

            Complete {
                copy(
                    MemberProfileResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun RefreshMemberProfile(memberId: Int) {
        GetMemberProfile(memberId)
    }

    fun UpsertBio(model: MemberProfileBioUpdateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateMember(model.MemberId)) return

        val bio = model.Bio?.trim()

        if (!bio.isNullOrEmpty() && bio.length > 500) {
            SetError("Hakkımda bilgisi en fazla 500 karakter olabilir.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertBio",
            operationType = "Profile.MemberProfile.Bio.Upsert",
            request = {
                memberProfileRepository.UpsertBioAsync(model.copy(Bio = bio))
            },
            onSuccess = onSuccess
        )
    }















    fun UpsertEducation(model: MemberProfileEducationUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertEducation",
            operationType = "Profile.MemberProfile.Education.Upsert",
            request = {
                memberProfileRepository.UpsertEducationAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertJobTitle(model: MemberProfileJobTitleUpdateModel, onSuccess: (() -> Unit)? = null) {
        val jobTitle = model.JobTitle?.trim()

        if (!jobTitle.isNullOrEmpty() && jobTitle.length > 100) {
            SetError("İş unvanı en fazla 100 karakter olabilir.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertJobTitle",
            operationType = "Profile.MemberProfile.JobTitle.Upsert",
            request = {
                memberProfileRepository.UpsertJobTitleAsync(model.copy(JobTitle = jobTitle))
            },
            onSuccess = onSuccess
        )
    }







    fun UpdateBasicProfile(languageId: Int, model: MemberUpdateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(model.MemberId)) return

        viewModelScope.launch {
            SetLoading("UpdateBasicProfile")

            Log.d(
                "ProfileProfession",
                "REQUEST memberId=${model.MemberId} name=${model.Name} surname=${model.Surname} profession=${model.Profession}"
            )

            val response = executeService.PostAsync(operationType = "Profile.Member.Update") {
                memberRepository.UpdateAsync(model)
            }

            Log.d(
                "ProfileProfession",
                "RESPONSE success=${response.Success} message=${response.Message} data=${response.Data}"
            )

            Complete {
                copy(ErrorMessage = response.Message.takeIf { !response.Success })
            }

            if (response.Success) {
                GetProfile(languageId = languageId, memberId = model.MemberId)
                onSuccess?.invoke()
            }
        }
    }

    fun GetGenders() {
        viewModelScope.launch {
            SetLoading("GetGenders")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescGenderRepository.GetSystemDescGenderListAsync()
            }

            Complete {
                copy(
                    GenderListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetEducations(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetEducations")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescEducationRepository.GetEducationsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    EducationListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }













    fun UpdateGender(languageId: Int, model: MemberUpdateGenderModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(model.MemberId)) return
        if (!ValidateId(model.GenderId, "Cinsiyet bilgisi seçiniz.")) return

        viewModelScope.launch {
            SetLoading("UpdateGender")

            val response = executeService.PostAsync(operationType = "Profile.Member.Gender.Update") {
                memberRepository.MemberUpdateGenderAsync(model)
            }

            Complete {
                copy(ErrorMessage = response.Message.takeIf { !response.Success })
            }

            if (response.Success) {
                GetProfile(languageId = languageId, memberId = model.MemberId)
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateBirthDate(languageId: Int, model: MemberUpdateBirthDateModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(model.MemberId)) return

        if (model.BirthDate.isNullOrBlank()) {
            SetError("Doğum tarihi bilgisi zorunludur.")
            return
        }

        viewModelScope.launch {
            SetLoading("UpdateBirthDate")

            val response = executeService.PostAsync(operationType = "Profile.Member.BirthDate.Update") {
                memberRepository.MemberUpdateBirthDateAsync(model)
            }

            Complete {
                copy(ErrorMessage = response.Message.takeIf { !response.Success })
            }

            if (response.Success) {
                GetProfile(languageId = languageId, memberId = model.MemberId)
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateAddress(languageId: Int, model: MemberUpdateAddressModel, onSuccess: (() -> Unit)? = null) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(model.MemberId)) return
        if (!ValidateId(model.CountryId, "Ülke bilgisi seçiniz.")) return
        if (!ValidateId(model.CityId, "Şehir bilgisi seçiniz.")) return

        viewModelScope.launch {
            SetLoading("UpdateAddress")

            val response = executeService.PostAsync(operationType = "Profile.Member.Address.Update") {
                memberRepository.MemberUpdateAddressAsync(model)
            }

            Complete {
                copy(ErrorMessage = response.Message.takeIf { !response.Success })
            }

            if (response.Success) {
                GetProfile(languageId = languageId, memberId = model.MemberId)
                onSuccess?.invoke()
            }
        }
    }

    fun UpdateProfession(
        languageId: Int,
        model: MemberUpdateProfessionModel,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateLanguage(languageId)) return
        if (!ValidateMember(model.MemberId)) return

        if (model.Profession.isBlank()) {
            SetError("Meslek bilgisi zorunludur.")
            return
        }

        viewModelScope.launch { SetLoading("UpdateProfession")

            val response = executeService.PostAsync(
                operationType = "Profile.Member.Profession.Update"
            ) {
                memberRepository.MemberUpdateProfessionAsync(
                    model.copy(Profession = model.Profession.trim())
                )
            }

            Complete {
                copy(
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                GetProfile(
                    languageId = languageId,
                    memberId = model.MemberId
                )
                onSuccess?.invoke()
            }
        }
    }

    fun GetMemberLanguages(
        memberId: Int,
        count: Int = 100
    ) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading("GetMemberLanguages")

            val response = executeService.GetAsync(cacheKey = "") {
                memberLanguageRepository.GetAccountLanguagesAsync(
                    memberId = memberId,
                    count = count
                )
            }

            Complete {
                copy(
                    MemberLanguageListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetLanguages(
        languageId: Int,
        count: Int = 500
    ) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetLanguages")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescLanguageRepository.GetSystemDescLanguagesAsync(
                    languageId = languageId,
                    count = count
                )
            }

            Complete {
                copy(
                    LanguageListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetLanguageLevels(languageId: Int, count: Int = 100) {
        viewModelScope.launch {
            SetLoading("GetLanguageLevels")

            val response = executeService.GetAsync(cacheKey = "") {
                
systemDescLanguageLevelRepository.GetSystemDescLanguageLevelsAsync(
                    languageId = languageId,
                    count = count
                )
            }

            Complete {
                copy(
                    LanguageLevelListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun InsertMemberLanguage(
        memberId: Int,
        languageId: Int,
        languageLevelId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(languageId, BBLocalization.Current.Get(key = "387bcc7b-e309-4099-8f1d-0ee062d4b7f4", fallback = ""))) return
        if (!ValidateId(languageLevelId, "Dil seviyesi seçiniz.")) return

        val model = MemberLanguageInsertModel(
            InsertedBy = memberId,
            StatusId = 2,
            MemberId = memberId,
            LanguageId = languageId,
            LanguageLevelId = languageLevelId
        )

        viewModelScope.launch {
            SetLoading("InsertMemberLanguage")

            val response = executeService.PostAsync(
                operationType = "Profile.MemberLanguage.Insert"
            ) {
                memberLanguageRepository.InsertAccountLanguageAsync(
                    memberId = memberId,
                    model = model
                )
            }

            Complete {
                copy(
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                GetMemberLanguages(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun DeleteMemberLanguage(
        memberId: Int,
        memberLanguageId: Int,
        onSuccess: (() -> Unit)? = null
    ) {
        if (!ValidateMember(memberId)) return
        if (!ValidateId(memberLanguageId, "Dil kaydı bulunamadı.")) return

        viewModelScope.launch {
            SetLoading("DeleteMemberLanguage")

            val response = executeService.PostAsync(
                operationType = "Profile.MemberLanguage.Delete"
            ) {
                memberLanguageRepository.DeleteAccountLanguageAsync(
                    memberId = memberId,
                    memberLanguageId = memberLanguageId
                )
            }

            Complete {
                copy(
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }

            if (response.Success) {
                GetMemberLanguages(memberId)
                onSuccess?.invoke()
            }
        }
    }

    fun ClearError() {
        _state.update {
            it.copy(ErrorMessage = null)
        }
    }

    fun ResetMemberProfile() {
        _state.update {
            it.copy(
                MemberProfileResult = null,
                ErrorMessage = null
            )
        }
    }

    fun Reset() {
        _state.value = ProfileControllerState()
    }

    private fun ExecuteMemberProfileUpdate(
        memberId: Int,
        currentAction: String,
        operationType: String,
        request: suspend () -> Result<MemberProfileDTO?>,
        onSuccess: (() -> Unit)?
    ) {
        if (!ValidateMember(memberId)) return

        viewModelScope.launch {
            SetLoading(currentAction)

            val response = executeService.PostAsync(operationType = operationType) {
                request()
            }

            Complete {
                copy(
                    MemberProfileResult = response,
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

    private fun Complete(update: ProfileControllerState.() -> ProfileControllerState) {
        _state.update {
            it.update().copy(
                IsLoading = false,
                CurrentAction = null
            )
        }
    }

    private fun ValidateLanguage(languageId: Int): Boolean {
        if (languageId > 0) return true

        SetError("Dil bilgisi bulunamadı.")
        return false
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
                CurrentAction = null,
                ErrorMessage = message
            )
        }
    }
}
