package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.ContractDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IContractRepository
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class ContractRepository(
    private val apiClient: ApiClient = ApiClient
) : IContractRepository {

    override suspend fun GetOrderStoreContractAsync(orderKey: String, storeKey: String): Result<ContractDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "GetOrderStoreContractAsync",
            query = "orderKey=$orderKey&storeKey=$storeKey"
        )
    }
}