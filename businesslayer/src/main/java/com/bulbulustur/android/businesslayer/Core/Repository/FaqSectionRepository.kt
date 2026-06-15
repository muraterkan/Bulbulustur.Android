package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.FaqSectionDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IFaqSectionRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.FaqSectionInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqSectionUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class FaqSectionRepository(
    private val apiClient: ApiClient = ApiClient
) : IFaqSectionRepository {

    override suspend fun GetFaqSectionListAsync(): Result<List<FaqSectionDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqSectionListAsync"
        )
    }

    override suspend fun GetFaqSectionByIdAsync(
        faqSectionId: Int
    ): Result<FaqSectionUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqSectionByIdAsync",
            query = "faqSectionId=$faqSectionId"
        )
    }

    override suspend fun GetFaqSectionByIdExtendedAsync(
        faqSectionId: Int
    ): Result<FaqSectionDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetFaqSectionByIdExtendedAsync",
            query = "faqSectionId=$faqSectionId"
        )
    }

    override suspend fun InsertAsync(
        model: FaqSectionInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: FaqSectionUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        faqSectionId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "faqSectionId=$faqSectionId"
        )
    }
}