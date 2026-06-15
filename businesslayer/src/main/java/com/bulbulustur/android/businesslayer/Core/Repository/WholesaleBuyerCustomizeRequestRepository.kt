package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleBuyerCustomizeRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleBuyerCustomizeRequestRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleBuyerCustomizeRequestInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleBuyerCustomizeRequestUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleBuyerCustomizeRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleBuyerCustomizeRequestRepository {

    override suspend fun GetWholesaleBuyerCustomizeRequestListAsync(): Result<List<WholesaleBuyerCustomizeRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBuyerCustomizeRequestListAsync"
        )
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBuyerCustomizeRequestByIdAsync",
            query = "wholesaleBuyerCustomizeRequestId=$wholesaleBuyerCustomizeRequestId"
        )
    }

    override suspend fun GetWholesaleBuyerCustomizeRequestByIdExtendedAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<WholesaleBuyerCustomizeRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleBuyerCustomizeRequestByIdExtendedAsync",
            query = "wholesaleBuyerCustomizeRequestId=$wholesaleBuyerCustomizeRequestId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleBuyerCustomizeRequestInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleBuyerCustomizeRequestUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleBuyerCustomizeRequestId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleBuyerCustomizeRequestId=$wholesaleBuyerCustomizeRequestId"
        )
    }
}