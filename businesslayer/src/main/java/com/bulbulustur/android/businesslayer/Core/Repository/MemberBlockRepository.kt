package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberBlockDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberBlockRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBlockInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBlockUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberBlockRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberBlockRepository {

    override suspend fun GetMemberBlockListAsync(): Result<List<MemberBlockDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBlockListAsync"
        )
    }

    override suspend fun GetMemberBlockByIdAsync(
        memberBlockId: Int
    ): Result<MemberBlockUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBlockByIdAsync",
            query = "memberBlockId=$memberBlockId"
        )
    }

    override suspend fun GetMemberBlockByIdExtendedAsync(
        memberBlockId: Int
    ): Result<MemberBlockDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberBlockByIdExtendedAsync",
            query = "memberBlockId=$memberBlockId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberBlockInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberBlockUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberBlockId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberBlockId=$memberBlockId"
        )
    }
}