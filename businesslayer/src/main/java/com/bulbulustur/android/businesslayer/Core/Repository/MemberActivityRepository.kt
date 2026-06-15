package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberActivityDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberActivityRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberActivityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberActivityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberActivityRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberActivityRepository {

    override suspend fun GetMemberActivityListAsync(): Result<List<MemberActivityDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberActivityListAsync"
        )
    }

    override suspend fun GetMemberActivityByIdAsync(
        memberActivityId: Int
    ): Result<MemberActivityUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberActivityByIdAsync",
            query = "memberActivityId=$memberActivityId"
        )
    }

    override suspend fun GetMemberActivityByIdExtendedAsync(
        memberActivityId: Int
    ): Result<MemberActivityDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberActivityByIdExtendedAsync",
            query = "memberActivityId=$memberActivityId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberActivityInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberActivityUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberActivityId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberActivityId=$memberActivityId"
        )
    }
}