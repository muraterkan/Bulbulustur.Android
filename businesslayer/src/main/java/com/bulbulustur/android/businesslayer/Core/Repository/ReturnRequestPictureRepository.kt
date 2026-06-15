package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.ReturnRequestPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ReturnRequestPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReturnRequestPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : IReturnRequestPictureRepository {

    override suspend fun GetReturnRequestPictureListAsync(): Result<List<ReturnRequestPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestPictureListAsync"
        )
    }

    override suspend fun GetReturnRequestPictureByIdAsync(
        ReturnRequestPictureId: Int
    ): Result<ReturnRequestPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestPictureByIdAsync",
            query = "ReturnRequestPictureId=$ReturnRequestPictureId"
        )
    }

    override suspend fun GetReturnRequestPictureByIdExtendedAsync(
        ReturnRequestPictureId: Int
    ): Result<ReturnRequestPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetReturnRequestPictureByIdExtendedAsync",
            query = "ReturnRequestPictureId=$ReturnRequestPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: ReturnRequestPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: ReturnRequestPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        ReturnRequestPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "ReturnRequestPictureId=$ReturnRequestPictureId"
        )
    }
}