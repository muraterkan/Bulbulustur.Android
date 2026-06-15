package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IWholesaleProductPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class WholesaleProductPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : IWholesaleProductPictureRepository {

    override suspend fun GetWholesaleProductPictureListAsync(): Result<List<WholesaleProductPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPictureListAsync"
        )
    }

    override suspend fun GetWholesaleProductPictureByIdAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPictureByIdAsync",
            query = "wholesaleProductPictureId=$wholesaleProductPictureId"
        )
    }

    override suspend fun GetWholesaleProductPictureByIdExtendedAsync(
        wholesaleProductPictureId: Int
    ): Result<WholesaleProductPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetWholesaleProductPictureByIdExtendedAsync",
            query = "wholesaleProductPictureId=$wholesaleProductPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: WholesaleProductPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: WholesaleProductPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        wholesaleProductPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "wholesaleProductPictureId=$wholesaleProductPictureId"
        )
    }
}