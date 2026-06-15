package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CpagesProductSpecialGroupRepository(
    private val apiClient: ApiClient = ApiClient
) : ICpagesProductSpecialGroupRepository {

    override suspend fun GetCpagesProductSpecialGroupListAsync(): Result<List<CpagesProductSpecialGroupDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupListAsync"
        )
    }

    override suspend fun GetCpagesProductSpecialGroupByIdAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupByIdAsync",
            query = "cpagesProductSpecialGroupId=$cpagesProductSpecialGroupId"
        )
    }

    override suspend fun GetCpagesProductSpecialGroupByIdExtendedAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<CpagesProductSpecialGroupDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupByIdExtendedAsync",
            query = "cpagesProductSpecialGroupId=$cpagesProductSpecialGroupId"
        )
    }

    override suspend fun InsertAsync(
        model: CpagesProductSpecialGroupInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CpagesProductSpecialGroupUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cpagesProductSpecialGroupId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cpagesProductSpecialGroupId=$cpagesProductSpecialGroupId"
        )
    }
}