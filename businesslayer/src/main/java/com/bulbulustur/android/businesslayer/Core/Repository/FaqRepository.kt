package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.FaqDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IFaqRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.FaqInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class FaqRepository(
    private val apiClient: ApiClient = ApiClient
) : IFaqRepository {

    override suspend fun GetFaqListAsync(): Result<List<FaqDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqListAsync"
        )
    }

    override suspend fun GetFaqByIdAsync(
        faqId: Int
    ): Result<FaqUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqByIdAsync",
            query = "faqId=$faqId"
        )
    }

    override suspend fun GetFaqByIdExtendedAsync(
        faqId: Int
    ): Result<FaqDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqByIdExtendedAsync",
            query = "faqId=$faqId"
        )
    }

    override suspend fun InsertAsync(
        model: FaqInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: FaqUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        faqId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "faqId=$faqId"
        )
    }
}