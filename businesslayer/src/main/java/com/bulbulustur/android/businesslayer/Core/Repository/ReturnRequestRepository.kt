package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ReturnRequestDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ReturnRequestRepository(
    private val apiClient: ApiClient = ApiClient
) : IReturnRequestRepository {

    override suspend fun GetReturnRequestsAsync(languageId: Int, memberId: Int, count: Int): Result<List<ReturnRequestDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_RETURN_BASE_URL,
            method = "GetReturnRequestsAsync",
            query = "languageId=$languageId&memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetReturnRequestByIdExtendedAsync(languageId: Int, memberId: Int, returnRequestId: Int): Result<ReturnRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_RETURN_BASE_URL,
            method = "GetReturnRequestByIdExtendedAsync",
            query = "languageId=$languageId&memberId=$memberId&returnRequestId=$returnRequestId"
        )
    }

    override suspend fun GetReturnRequestSimpleAsync(memberId: Int, returnRequestId: Int): Result<ReturnRequestDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_RETURN_BASE_URL,
            method = "GetReturnRequestSimpleAsync",
            query = "memberId=$memberId&returnRequestId=$returnRequestId"
        )
    }

    override suspend fun InsertReturnRequestAsync(languageId: Int, memberId: Int, returnRequest: ReturnRequestDTO): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_RETURN_BASE_URL,
            method = "InsertReturnRequestAsync",
            query = "languageId=$languageId&memberId=$memberId",
            data = returnRequest
        )
    }
}