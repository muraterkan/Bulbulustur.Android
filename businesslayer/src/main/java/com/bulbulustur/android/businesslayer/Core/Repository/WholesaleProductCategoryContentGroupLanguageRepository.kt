package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentGroupLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentGroupLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCategoryContentGroupLanguageRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCategoryContentGroupLanguageRepository {

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageListAsync(): Result<List<WholesaleProductCategoryContentGroupLanguageDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupLanguageListAsync"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupLanguageByIdAsync",
            query = "wholesaleProductCategoryContentGroupLanguageId=$wholesaleProductCategoryContentGroupLanguageId"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<WholesaleProductCategoryContentGroupLanguageDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupLanguageByIdExtendedAsync",
            query = "wholesaleProductCategoryContentGroupLanguageId=$wholesaleProductCategoryContentGroupLanguageId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductCategoryContentGroupLanguageInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductCategoryContentGroupLanguageUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductCategoryContentGroupLanguageId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductCategoryContentGroupLanguageId=$wholesaleProductCategoryContentGroupLanguageId"
        )
    }
}