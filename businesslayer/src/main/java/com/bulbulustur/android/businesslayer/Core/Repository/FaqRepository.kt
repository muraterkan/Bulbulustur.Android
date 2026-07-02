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

    override suspend fun GetFaqs(languageId: Int, faqSectionId: Int, count: Int): Result<List<FaqDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "GetFaqs",
            query = "languageId=$languageId&faqSectionId=$faqSectionId&count=$count"
        )
    }

    override suspend fun GetFaqById(languageId: Int, helpId: Int): Result<FaqUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "GetFaqById",
            query = "languageId=$languageId&helpId=$helpId"
        )
    }

    override suspend fun GetFaqByIdExtended(languageId: Int, helpId: Int): Result<FaqDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "GetFaqByIdExtended",
            query = "languageId=$languageId&helpId=$helpId"
        )
    }

    override suspend fun Insert(model: FaqInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "FaqInsert",
            data = model
        )
    }

    override suspend fun Update(model: FaqUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "FaqUpdate",
            data = model
        )
    }

    override suspend fun Delete(helpId: Int): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.FAQ_BASE_URL,
            method = "FaqDelete",
            data = helpId
        )
    }
}