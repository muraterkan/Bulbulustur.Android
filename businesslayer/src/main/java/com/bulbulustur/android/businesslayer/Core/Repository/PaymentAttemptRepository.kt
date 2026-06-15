package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.PaymentAttemptDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IPaymentAttemptRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.PaymentAttemptInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.PaymentAttemptUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class PaymentAttemptRepository(
    private val apiClient: ApiClient = ApiClient
) : IPaymentAttemptRepository {

    override suspend fun GetPaymentAttemptListAsync(): Result<List<PaymentAttemptDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetPaymentAttemptListAsync"
        )
    }

    override suspend fun GetPaymentAttemptByIdAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetPaymentAttemptByIdAsync",
            query = "paymentAttemptId=$paymentAttemptId"
        )
    }

    override suspend fun GetPaymentAttemptByIdExtendedAsync(
        paymentAttemptId: Int
    ): Result<PaymentAttemptDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetPaymentAttemptByIdExtendedAsync",
            query = "paymentAttemptId=$paymentAttemptId"
        )
    }

    override suspend fun InsertAsync(
        model: PaymentAttemptInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: PaymentAttemptUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        paymentAttemptId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "paymentAttemptId=$paymentAttemptId"
        )
    }
}