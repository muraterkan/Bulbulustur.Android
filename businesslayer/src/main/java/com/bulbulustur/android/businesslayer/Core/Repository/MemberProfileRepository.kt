package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberProfileDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberProfileRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileBioUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileEducationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileJobTitleUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberProfileUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberProfileRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberProfileRepository {

    override suspend fun GetMemberProfilesAsync(count: Int): Result<List<MemberProfileDTO>> =
        apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfilesAsync",
            query = "count=$count"
        )

    override suspend fun GetMemberProfileByIdAsync(memberProfileId: Int): Result<MemberProfileUpdateModel?> =
        apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByIdAsync",
            query = "memberProfileId=$memberProfileId"
        )

    override suspend fun GetMemberProfileByIdExtendedAsync(memberProfileId: Int): Result<MemberProfileDTO?> =
        apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByIdExtendedAsync",
            query = "memberProfileId=$memberProfileId"
        )

    override suspend fun GetMemberProfileByMemberIdAsync(memberId: Int): Result<MemberProfileDTO?> =
        apiClient.GetAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "GetMemberProfileByMemberIdAsync",
            query = "memberId=$memberId"
        )

    override suspend fun UpsertBioAsync(model: MemberProfileBioUpdateModel): Result<MemberProfileDTO?> =
        apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileBioUpsertAsync",
            data = model
        )

    override suspend fun UpsertEducationAsync(model: MemberProfileEducationUpdateModel): Result<MemberProfileDTO?> =
        apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileEducationUpsertAsync",
            data = model
        )

    override suspend fun UpsertJobTitleAsync(model: MemberProfileJobTitleUpdateModel): Result<MemberProfileDTO?> =
        apiClient.PostAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileJobTitleUpsertAsync",
            data = model
        )

    override suspend fun DeleteAsync(memberProfileId: Int): Result<Unit> =
        apiClient.DeleteAsync(
            baseUrl = ApiRoutes.PURE_MEMBER_BASE_URL,
            method = "MemberProfileDelete",
            query = "memberProfileId=$memberProfileId"
        )
}
