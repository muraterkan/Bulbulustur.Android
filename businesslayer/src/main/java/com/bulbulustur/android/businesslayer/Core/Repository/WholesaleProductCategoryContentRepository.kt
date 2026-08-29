package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategoryContentDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductCategoryContentRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCategoryContentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCategoryContentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductCategoryContentRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductCategoryContentRepository {

    override suspend fun GetWholesaleProductCategoryContentListAsync(): Result<List<WholesaleProductCategoryContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentListAsync"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentByIdAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentByIdAsync",
            query = "wholesaleProductCategoryContentId=$wholesaleProductCategoryContentId"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentByIdExtendedAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<WholesaleProductCategoryContentDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductCategoryContentByIdExtendedAsync",
            query = "wholesaleProductCategoryContentId=$wholesaleProductCategoryContentId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductCategoryContentInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductCategoryContentUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductCategoryContentId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductCategoryContentId=$wholesaleProductCategoryContentId"
        )
    }
    override suspend fun GetWholesaleProductCategoryContentsAsync(languageId: Int, productCategoryId: Int, groupCount: Int, productCount: Int): Result<WholesaleProductCategoryContentDTO> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_CATEGORY_CONTENT_BASE_URL,
            method = "GetWholesaleProductCategoryContentsAsync",
            query = "languageId=$languageId&productCategoryId=$productCategoryId&groupCount=$groupCount&productCount=$productCount"
        )
    }

    override suspend fun GetWholesaleProductCategoryContentsPagedAsync(productCategoryContentGroupId: Int, page: Int, pageSize: Int): Result<PaginatedList<WholesaleProductCategoryContentDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_PRODUCT_CATEGORY_CONTENT_BASE_URL,
            method = "GetWholesaleProductCategoryContentsPagedAsync",
            query = "productCategoryContentGroupId=$productCategoryContentGroupId&page=$page&pageSize=$pageSize"
        )
    }
}