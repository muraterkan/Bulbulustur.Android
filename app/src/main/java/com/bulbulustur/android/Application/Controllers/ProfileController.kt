package com.bulbulustur.android.Application.Controllers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.DTO.MemberLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescLanguageLevelDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescAlcoholHabitDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyHairDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescBodyTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescChildrenPreferenceDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescDietTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescEducationDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescExerciseHabitDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMaritalStatusDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPubicHairDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescRelationshipTypeDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescReligionDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSkinToneDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescSmokingHabitDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescLanguageLevelRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescAlcoholHabitRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBodyHairRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescBodyTypeRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescChildrenPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescDietTypeRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescEducationRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescExerciseHabitRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescGenderRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescMaritalStatusRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescPubicHairRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescRelationshipTypeRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescReligionRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSkinToneRepository
import com.bulbulustur.android.businesslayer.Core.Interface.ISystemDescSmokingHabitRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileAlcoholHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileArmpitHairPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBioUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyHairPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyHairUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBodyTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBreastSizeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileChildrenPreferenceUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileCoupleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileDietTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileEducationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileExerciseHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileHeightUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileJobTitleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileMaritalStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePenisSizeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePiercingUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfilePubicHairUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileRelationshipTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileReligionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileSkinToneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileSmokingHabitUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileTattooUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileWeightUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateAddressModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateBirthDateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateGenderModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateProfessionModel
import com.bulbulustur.android.businesslayer.Core.Repository.MemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescLanguageLevelRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescAlcoholHabitRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescBodyHairRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescBodyTypeRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescChildrenPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescDietTypeRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescEducationRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescExerciseHabitRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescMaritalStatusRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescPubicHairRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescRelationshipTypeRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescReligionRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescSkinToneRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescSmokingHabitRepository
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
    val MaritalStatusListResult: Result<List<SystemDescMaritalStatusDTO>>? = null,
    val RelationshipTypeListResult: Result<List<SystemDescRelationshipTypeDTO>>? = null,
    val ChildrenPreferenceListResult: Result<List<SystemDescChildrenPreferenceDTO>>? = null,
    val DietTypeListResult: Result<List<SystemDescDietTypeDTO>>? = null,
    val ExerciseHabitListResult: Result<List<SystemDescExerciseHabitDTO>>? = null,
    val AlcoholHabitListResult: Result<List<SystemDescAlcoholHabitDTO>>? = null,
    val SmokingHabitListResult: Result<List<SystemDescSmokingHabitDTO>>? = null,
    val ReligionListResult: Result<List<SystemDescReligionDTO>>? = null,
    val BodyHairListResult: Result<List<SystemDescBodyHairDTO>>? = null,
    val BodyTypeListResult: Result<List<SystemDescBodyTypeDTO>>? = null,
    val SkinToneListResult: Result<List<SystemDescSkinToneDTO>>? = null,
    val PubicHairListResult: Result<List<SystemDescPubicHairDTO>>? = null,
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

    val MaritalStatuses: List<SystemDescMaritalStatusDTO>
        get() = MaritalStatusListResult?.Data.orEmpty()

    val RelationshipTypes: List<SystemDescRelationshipTypeDTO>
        get() = RelationshipTypeListResult?.Data.orEmpty()

    val ChildrenPreferences: List<SystemDescChildrenPreferenceDTO>
        get() = ChildrenPreferenceListResult?.Data.orEmpty()

    val DietTypes: List<SystemDescDietTypeDTO>
        get() = DietTypeListResult?.Data.orEmpty()

    val ExerciseHabits: List<SystemDescExerciseHabitDTO>
        get() = ExerciseHabitListResult?.Data.orEmpty()

    val AlcoholHabits: List<SystemDescAlcoholHabitDTO>
        get() = AlcoholHabitListResult?.Data.orEmpty()

    val SmokingHabits: List<SystemDescSmokingHabitDTO>
        get() = SmokingHabitListResult?.Data.orEmpty()

    val Religions: List<SystemDescReligionDTO>
        get() = ReligionListResult?.Data.orEmpty()

    val BodyHairs: List<SystemDescBodyHairDTO>
        get() = BodyHairListResult?.Data.orEmpty()

    val BodyTypes: List<SystemDescBodyTypeDTO>
        get() = BodyTypeListResult?.Data.orEmpty()

    val SkinTones: List<SystemDescSkinToneDTO>
        get() = SkinToneListResult?.Data.orEmpty()

    val PubicHairs: List<SystemDescPubicHairDTO>
        get() = PubicHairListResult?.Data.orEmpty()

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
    private val systemDescMaritalStatusRepository: ISystemDescMaritalStatusRepository = SystemDescMaritalStatusRepository(),
    private val systemDescRelationshipTypeRepository: ISystemDescRelationshipTypeRepository = SystemDescRelationshipTypeRepository(),
    private val systemDescChildrenPreferenceRepository: ISystemDescChildrenPreferenceRepository = SystemDescChildrenPreferenceRepository(),
    private val systemDescDietTypeRepository: ISystemDescDietTypeRepository = SystemDescDietTypeRepository(),
    private val systemDescExerciseHabitRepository: ISystemDescExerciseHabitRepository = SystemDescExerciseHabitRepository(),
    private val systemDescAlcoholHabitRepository: ISystemDescAlcoholHabitRepository = SystemDescAlcoholHabitRepository(),
    private val systemDescSmokingHabitRepository: ISystemDescSmokingHabitRepository = SystemDescSmokingHabitRepository(),
    private val systemDescReligionRepository: ISystemDescReligionRepository = SystemDescReligionRepository(),
    private val systemDescBodyHairRepository: ISystemDescBodyHairRepository = SystemDescBodyHairRepository(),
    private val systemDescBodyTypeRepository: ISystemDescBodyTypeRepository = SystemDescBodyTypeRepository(),
    private val systemDescSkinToneRepository: ISystemDescSkinToneRepository = SystemDescSkinToneRepository(),
    private val systemDescPubicHairRepository: ISystemDescPubicHairRepository = SystemDescPubicHairRepository(),
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

    fun UpsertHeight(model: MemberProfileHeightUpdateModel, onSuccess: (() -> Unit)? = null) {
        val height = model.Height

        if (height != null && height <= 0) {
            SetError("Boy bilgisi geçerli olmalıdır.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertHeight",
            operationType = "Profile.MemberProfile.Height.Upsert",
            request = {
                memberProfileRepository.UpsertHeightAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertWeight(model: MemberProfileWeightUpdateModel, onSuccess: (() -> Unit)? = null) {
        val weight = model.Weight

        if (weight != null && weight <= 0) {
            SetError("Kilo bilgisi geçerli olmalıdır.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertWeight",
            operationType = "Profile.MemberProfile.Weight.Upsert",
            request = {
                memberProfileRepository.UpsertWeightAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertBodyType(model: MemberProfileBodyTypeUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertBodyType",
            operationType = "Profile.MemberProfile.BodyType.Upsert",
            request = {
                memberProfileRepository.UpsertBodyTypeAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertSkinTone(model: MemberProfileSkinToneUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertSkinTone",
            operationType = "Profile.MemberProfile.SkinTone.Upsert",
            request = {
                memberProfileRepository.UpsertSkinToneAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertPiercing(model: MemberProfilePiercingUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertPiercing",
            operationType = "Profile.MemberProfile.Piercing.Upsert",
            request = {
                memberProfileRepository.UpsertPiercingAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertTattoo(model: MemberProfileTattooUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertTattoo",
            operationType = "Profile.MemberProfile.Tattoo.Upsert",
            request = {
                memberProfileRepository.UpsertTattooAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertReligion(model: MemberProfileReligionUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertReligion",
            operationType = "Profile.MemberProfile.Religion.Upsert",
            request = {
                memberProfileRepository.UpsertReligionAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertDietType(model: MemberProfileDietTypeUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertDietType",
            operationType = "Profile.MemberProfile.DietType.Upsert",
            request = {
                memberProfileRepository.UpsertDietTypeAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertExerciseHabit(model: MemberProfileExerciseHabitUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertExerciseHabit",
            operationType = "Profile.MemberProfile.ExerciseHabit.Upsert",
            request = {
                memberProfileRepository.UpsertExerciseHabitAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertAlcoholHabit(model: MemberProfileAlcoholHabitUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertAlcoholHabit",
            operationType = "Profile.MemberProfile.AlcoholHabit.Upsert",
            request = {
                memberProfileRepository.UpsertAlcoholHabitAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertSmokingHabit(model: MemberProfileSmokingHabitUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertSmokingHabit",
            operationType = "Profile.MemberProfile.SmokingHabit.Upsert",
            request = {
                memberProfileRepository.UpsertSmokingHabitAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertMaritalStatus(model: MemberProfileMaritalStatusUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertMaritalStatus",
            operationType = "Profile.MemberProfile.MaritalStatus.Upsert",
            request = {
                memberProfileRepository.UpsertMaritalStatusAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertRelationshipType(model: MemberProfileRelationshipTypeUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertRelationshipType",
            operationType = "Profile.MemberProfile.RelationshipType.Upsert",
            request = {
                memberProfileRepository.UpsertRelationshipTypeAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertChildrenPreference(model: MemberProfileChildrenPreferenceUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertChildrenPreference",
            operationType = "Profile.MemberProfile.ChildrenPreference.Upsert",
            request = {
                memberProfileRepository.UpsertChildrenPreferenceAsync(model)
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

    fun UpsertBodyHair(model: MemberProfileBodyHairUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertBodyHair",
            operationType = "Profile.MemberProfile.BodyHair.Upsert",
            request = {
                memberProfileRepository.UpsertBodyHairAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertPubicHair(model: MemberProfilePubicHairUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertPubicHair",
            operationType = "Profile.MemberProfile.PubicHair.Upsert",
            request = {
                memberProfileRepository.UpsertPubicHairAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertArmpitHairPreference(model: MemberProfileArmpitHairPreferenceUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertArmpitHairPreference",
            operationType = "Profile.MemberProfile.ArmpitHairPreference.Upsert",
            request = {
                memberProfileRepository.UpsertArmpitHairPreferenceAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertBodyHairPreference(model: MemberProfileBodyHairPreferenceUpdateModel, onSuccess: (() -> Unit)? = null) {
        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertBodyHairPreference",
            operationType = "Profile.MemberProfile.BodyHairPreference.Upsert",
            request = {
                memberProfileRepository.UpsertBodyHairPreferenceAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertPenisSize(model: MemberProfilePenisSizeUpdateModel, onSuccess: (() -> Unit)? = null) {
        val penisSize = model.PenisSize

        if (penisSize != null && penisSize <= 0) {
            SetError("Penis ölçüsü geçerli olmalıdır.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertPenisSize",
            operationType = "Profile.MemberProfile.PenisSize.Upsert",
            request = {
                memberProfileRepository.UpsertPenisSizeAsync(model)
            },
            onSuccess = onSuccess
        )
    }

    fun UpsertBreastSize(model: MemberProfileBreastSizeUpdateModel, onSuccess: (() -> Unit)? = null) {
        val breastSize = model.BreastSize?.trim()

        if (!breastSize.isNullOrEmpty() && breastSize.length > 10) {
            SetError("Göğüs ölçüsü en fazla 10 karakter olabilir.")
            return
        }

        ExecuteMemberProfileUpdate(
            memberId = model.MemberId,
            currentAction = "UpsertBreastSize",
            operationType = "Profile.MemberProfile.BreastSize.Upsert",
            request = {
                memberProfileRepository.UpsertBreastSizeAsync(model.copy(BreastSize = breastSize))
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

    fun GetMaritalStatuses(languageId: Int) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetMaritalStatuses")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescMaritalStatusRepository.GetSystemDescMaritalStatusListAsync(
                    languageId = languageId,
                    count = 100
                )
            }

            Complete {
                copy(
                    MaritalStatusListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetRelationshipTypes(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetRelationshipTypes")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescRelationshipTypeRepository.GetRelationshipTypesAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    RelationshipTypeListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetChildrenPreferences(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetChildrenPreferences")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescChildrenPreferenceRepository.GetChildrenPreferencesAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    ChildrenPreferenceListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetDietTypes(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetDietTypes")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescDietTypeRepository.GetDietTypesAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    DietTypeListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetExerciseHabits(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetExerciseHabits")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescExerciseHabitRepository.GetExerciseHabitsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    ExerciseHabitListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetAlcoholHabits(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetAlcoholHabits")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescAlcoholHabitRepository.GetAlcoholHabitsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    AlcoholHabitListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetSmokingHabits(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetSmokingHabits")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescSmokingHabitRepository.GetSmokingHabitsAsync(
                    languageId = languageId,
                    count = count
                )
            }

            Complete {
                copy(
                    SmokingHabitListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetReligions(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetReligions")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescReligionRepository.GetReligionsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    ReligionListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetBodyTypes(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetBodyTypes")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescBodyTypeRepository.GetBodyTypesAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    BodyTypeListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetSkinTones(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetSkinTones")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescSkinToneRepository.GetSkinTonesAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    SkinToneListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetBodyHairs(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetBodyHairs")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescBodyHairRepository.GetBodyHairsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    BodyHairListResult = response,
                    ErrorMessage = response.Message.takeIf { !response.Success }
                )
            }
        }
    }

    fun GetPubicHairs(languageId: Int, count: Int = 100) {
        if (!ValidateLanguage(languageId)) return

        viewModelScope.launch {
            SetLoading("GetPubicHairs")

            val response = executeService.GetAsync(cacheKey = "") {
                systemDescPubicHairRepository.GetPubicHairsAsync(languageId = languageId, count = count)
            }

            Complete {
                copy(
                    PubicHairListResult = response,
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
        if (!ValidateId(languageId, "Dil seçiniz.")) return
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
