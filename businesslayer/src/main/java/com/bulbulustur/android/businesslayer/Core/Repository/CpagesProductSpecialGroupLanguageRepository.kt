package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CpagesProductSpecialGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICpagesProductSpecialGroupLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CpagesProductSpecialGroupLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CpagesProductSpecialGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CpagesProductSpecialGroupLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : ICpagesProductSpecialGroupLanguageRepository {

    override suspend fun GetCpagesProductSpecialGroupLanguageListAsync(): Result<List<CpagesProductSpecialGroupLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupLanguageListAsync"
        )
    }

    override suspend fun GetCpagesProductSpecialGroupLanguageByIdAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupLanguageByIdAsync",
            query = "cpagesProductSpecialGroupLanguageId=$cpagesProductSpecialGroupLanguageId"
        )
    }

    override suspend fun GetCpagesProductSpecialGroupLanguageByIdExtendedAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<CpagesProductSpecialGroupLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCpagesProductSpecialGroupLanguageByIdExtendedAsync",
            query = "cpagesProductSpecialGroupLanguageId=$cpagesProductSpecialGroupLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: CpagesProductSpecialGroupLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CpagesProductSpecialGroupLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        cpagesProductSpecialGroupLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "cpagesProductSpecialGroupLanguageId=$cpagesProductSpecialGroupLanguageId"
        )
    }
}