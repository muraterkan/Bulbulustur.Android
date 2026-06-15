package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentGroupDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentGroupRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentGroupInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentGroupUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCategoryContentGroupRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCategoryContentGroupRepository {

    override suspend fun GetWholesaleProductCategoryContentGroupListAsync(): Result<List<WholesaleProductCategoryContentGroupDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupListAsync"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentGroupByIdAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupByIdAsync",
            query = "wholesaleProductCategoryContentGroupId=$wholesaleProductCategoryContentGroupId"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentGroupByIdExtendedAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<WholesaleProductCategoryContentGroupDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentGroupByIdExtendedAsync",
            query = "wholesaleProductCategoryContentGroupId=$wholesaleProductCategoryContentGroupId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductCategoryContentGroupInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductCategoryContentGroupUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductCategoryContentGroupId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductCategoryContentGroupId=$wholesaleProductCategoryContentGroupId"
        )
    }
}