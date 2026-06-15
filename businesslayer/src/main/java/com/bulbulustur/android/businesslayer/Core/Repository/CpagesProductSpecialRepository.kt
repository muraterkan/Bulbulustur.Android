package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CpagesProductSpecialRepository(
    private val apiClient: ApiClient = ApiClient
) : ICpagesProductSpecialRepository {

    override suspend fun GetCpagesProductSpecialListAsync(): Result<List<CpagesProductSpecialDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialListAsync"
        )
    }

    override suspend fun GetCpagesProductSpecialByIdAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialByIdAsync",
            query = "cpagesProductSpecialId=$cpagesProductSpecialId"
        )
    }

    override suspend fun GetCpagesProductSpecialByIdExtendedAsync(
        cpagesProductSpecialId: Int
    ): Result<CpagesProductSpecialDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialByIdExtendedAsync",
            query = "cpagesProductSpecialId=$cpagesProductSpecialId"
        )
    }

    override suspend fun InsertAsync(
        model: CpagesProductSpecialInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CpagesProductSpecialUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cpagesProductSpecialId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cpagesProductSpecialId=$cpagesProductSpecialId"
        )
    }
}