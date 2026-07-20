package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.*
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberProfileRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberProfileRepository {

    override suspend fun GetMemberProfilesAsync(count: Int): Result<List<MemberProfileDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfilesAsync",
            query = "count=$count"
        )
    }

    override suspend fun GetMemberProfileByIdAsync(memberProfileId: Int): Result<MemberProfileUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByIdAsync",
            query = "memberProfileId=$memberProfileId"
        )
    }

    override suspend fun GetMemberProfileByIdExtendedAsync(memberProfileId: Int): Result<MemberProfileDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByIdExtendedAsync",
            query = "memberProfileId=$memberProfileId"
        )
    }

    override suspend fun GetMemberProfileByMemberIdAsync(memberId: Int): Result<MemberProfileDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByMemberIdAsync",
            query = "memberId=$memberId"
        )
    }

    override suspend fun UpsertBioAsync(model: MemberProfileBioUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBioUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertCoupleAsync(model: MemberProfileCoupleUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileCoupleUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertHeightAsync(model: MemberProfileHeightUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileHeightUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertWeightAsync(model: MemberProfileWeightUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileWeightUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertBodyTypeAsync(model: MemberProfileBodyTypeUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBodyTypeUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertSkinToneAsync(model: MemberProfileSkinToneUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileSkinToneUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertPiercingAsync(model: MemberProfilePiercingUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfilePiercingUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertTattooAsync(model: MemberProfileTattooUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileTattooUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertReligionAsync(model: MemberProfileReligionUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileReligionUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertDietTypeAsync(model: MemberProfileDietTypeUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileDietTypeUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertExerciseHabitAsync(model: MemberProfileExerciseHabitUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileExerciseHabitUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertAlcoholHabitAsync(model: MemberProfileAlcoholHabitUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileAlcoholHabitUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertSmokingHabitAsync(model: MemberProfileSmokingHabitUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileSmokingHabitUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertMaritalStatusAsync(model: MemberProfileMaritalStatusUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileMaritalStatusUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertRelationshipTypeAsync(model: MemberProfileRelationshipTypeUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileRelationshipTypeUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertChildrenPreferenceAsync(model: MemberProfileChildrenPreferenceUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileChildrenPreferenceUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertEducationAsync(model: MemberProfileEducationUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileEducationUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertJobTitleAsync(model: MemberProfileJobTitleUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileJobTitleUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertBodyHairAsync(model: MemberProfileBodyHairUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBodyHairUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertPubicHairAsync(model: MemberProfilePubicHairUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfilePubicHairUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertArmpitHairPreferenceAsync(model: MemberProfileArmpitHairPreferenceUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileArmpitHairPreferenceUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertBodyHairPreferenceAsync(model: MemberProfileBodyHairPreferenceUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBodyHairPreferenceUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertPenisSizeAsync(model: MemberProfilePenisSizeUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfilePenisSizeUpsertAsync",
            data = model
        )
    }

    override suspend fun UpsertBreastSizeAsync(model: MemberProfileBreastSizeUpdateModel): Result<MemberProfileDTO?> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBreastSizeUpsertAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(memberProfileId: Int): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileDelete",
            query = "memberProfileId=$memberProfileId"
        )
    }
}