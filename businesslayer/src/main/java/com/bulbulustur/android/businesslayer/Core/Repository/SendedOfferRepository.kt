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

    override suspend fun GetSendedOffersAsync(buyerRequestKey: String, count: Int): Result<List<SendedOfferDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/GetSendedOffersAsync",
            query = "buyerRequestKey=$buyerRequestKey&count=$count"
        )
    }

    override suspend fun GetSendedOfferByIdAsync(sendedOfferId: Int): Result<SendedOfferUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/GetSendedOfferByIdAsync",
            query = "sendedOfferId=$sendedOfferId"
        )
    }

    override suspend fun GetSendedOfferByIdExtendedAsync(sendedOfferId: Int): Result<SendedOfferDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/GetSendedOfferByIdExtendedAsync",
            query = "sendedOfferId=$sendedOfferId"
        )
    }

    override suspend fun InsertAsync(model: SendedOfferInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/SendedOfferInsert",
            data = model
        )
    }

    override suspend fun UpdateAsync(model: SendedOfferUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/SendedOfferUpdate",
            data = model
        )
    }

    override suspend fun DeleteAsync(model: SendedOfferUpdateModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.B2B_BASE_URL,
            method = "SendedOffer/DeleteSendedOffer",
            data = model
        )
    }
}