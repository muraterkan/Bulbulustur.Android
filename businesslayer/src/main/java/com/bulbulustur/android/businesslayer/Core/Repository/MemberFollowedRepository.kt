package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberFollowedDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberFollowedRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberFollowedInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberFollowedUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberFollowedRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberFollowedRepository {

    override suspend fun GetMemberFollowedListAsync(): Result<List<MemberFollowedDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberFollowedListAsync"
        )
    }

    override suspend fun GetMemberFollowedByIdAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberFollowedByIdAsync",
            query = "memberFollowedId=$memberFollowedId"
        )
    }

    override suspend fun GetMemberFollowedByIdExtendedAsync(
        memberFollowedId: Int
    ): Result<MemberFollowedDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberFollowedByIdExtendedAsync",
            query = "memberFollowedId=$memberFollowedId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberFollowedInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberFollowedUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberFollowedId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberFollowedId=$memberFollowedId"
        )
    }
}