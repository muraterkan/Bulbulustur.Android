package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.TooltipDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ITooltipRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.TooltipInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TooltipUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class TooltipRepository(
    private val apiClient: ApiClient = ApiClient
) : ITooltipRepository {

    override suspend fun GetTooltipListAsync(): Result<List<TooltipDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTooltipListAsync"
        )
    }

    override suspend fun GetTooltipByIdAsync(
        tooltipId: Int
    ): Result<TooltipUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTooltipByIdAsync",
            query = "tooltipId=$tooltipId"
        )
    }

    override suspend fun GetTooltipByIdExtendedAsync(
        tooltipId: Int
    ): Result<TooltipDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetTooltipByIdExtendedAsync",
            query = "tooltipId=$tooltipId"
        )
    }

    override suspend fun InsertAsync(
        model: TooltipInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: TooltipUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        tooltipId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "tooltipId=$tooltipId"
        )
    }
}