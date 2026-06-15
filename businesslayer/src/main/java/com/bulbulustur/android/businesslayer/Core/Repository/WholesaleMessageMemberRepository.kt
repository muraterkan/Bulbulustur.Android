package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleMessageMemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleMessageMemberRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleMessageMemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleMessageMemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleMessageMemberRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleMessageMemberRepository {

    override suspend fun GetWholesaleMessageMemberListAsync(): Result<List<WholesaleMessageMemberDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageMemberListAsync"
        )
    }

    override suspend fun GetWholesaleMessageMemberByIdAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageMemberByIdAsync",
            query = "wholesaleMessageMemberId=$wholesaleMessageMemberId"
        )
    }

    override suspend fun GetWholesaleMessageMemberByIdExtendedAsync(
        wholesaleMessageMemberId: Int
    ): Result<WholesaleMessageMemberDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleMessageMemberByIdExtendedAsync",
            query = "wholesaleMessageMemberId=$wholesaleMessageMemberId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleMessageMemberInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleMessageMemberUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleMessageMemberId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleMessageMemberId=$wholesaleMessageMemberId"
        )
    }
}