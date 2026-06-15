package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberAddressDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberAddressInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberAddressUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class MemberAddressRepository(
    private val apiClient: ApiClient = ApiClient
) : IMemberAddressRepository {

    override suspend fun GetMemberAddressListAsync(): Result<List<MemberAddressDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAddressListAsync"
        )
    }

    override suspend fun GetMemberAddressByIdAsync(
        memberAddressId: Int
    ): Result<MemberAddressUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAddressByIdAsync",
            query = "memberAddressId=$memberAddressId"
        )
    }

    override suspend fun GetMemberAddressByIdExtendedAsync(
        memberAddressId: Int
    ): Result<MemberAddressDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetMemberAddressByIdExtendedAsync",
            query = "memberAddressId=$memberAddressId"
        )
    }

    override suspend fun InsertAsync(
        model: MemberAddressInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: MemberAddressUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        memberAddressId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "memberAddressId=$memberAddressId"
        )
    }
}