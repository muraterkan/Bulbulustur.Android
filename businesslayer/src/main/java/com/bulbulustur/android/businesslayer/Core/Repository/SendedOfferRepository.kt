package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.SendedOfferDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ISendedOfferRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SendedOfferInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SendedOfferUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class SendedOfferRepository(
    private val apiClient: ApiClient = ApiClient
) : ISendedOfferRepository {

    override suspend fun GetSendedOfferListAsync(): Result<List<SendedOfferDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSendedOfferListAsync"
        )
    }

    override suspend fun GetSendedOfferByIdAsync(
        sendedOfferId: Int
    ): Result<SendedOfferUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSendedOfferByIdAsync",
            query = "sendedOfferId=$sendedOfferId"
        )
    }

    override suspend fun GetSendedOfferByIdExtendedAsync(
        sendedOfferId: Int
    ): Result<SendedOfferDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetSendedOfferByIdExtendedAsync",
            query = "sendedOfferId=$sendedOfferId"
        )
    }

    override suspend fun InsertAsync(
        model: SendedOfferInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: SendedOfferUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        sendedOfferId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "sendedOfferId=$sendedOfferId"
        )
    }
}