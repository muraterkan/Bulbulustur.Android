package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreLineDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class OrderRepository(
    private val apiClient: ApiClient = ApiClient
) : IOrderRepository {

    override suspend fun GetOrdersByMemberIdAsync(memberId: Int, count: Int): Result<List<OrderDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "GetOrdersByMemberIdAsync",
            query = "memberId=$memberId&count=$count"
        )
    }

    override suspend fun GetOrderStoresAsync(orderKey: String): Result<List<OrderStoreDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "GetOrderStoresAsync",
            query = "orderKey=$orderKey"
        )
    }

    override suspend fun GetOrderTrackingAsync(cargoTrackingNumber: Int, memberId: Int): Result<OrderStoreLineDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "GetOrderTrackingAsync",
            query = "cargoTrackingNumber=$cargoTrackingNumber&memberId=$memberId"
        )
    }

    override suspend fun GetOrderCancelationTypes(count: Int): Result<List<SystemDescOrderCancelationTypeDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "GetOrderCancelationTypes",
            query = "count=$count"
        )
    }

    override suspend fun InsertOrderCancelationAsync(languageId: Int, memberId: Int, insertModel: OrderCancelationInsertModel): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_ORDER_BASE_URL,
            method = "InsertOrderCancelationAsync",
            query = "languageId=$languageId&memberId=$memberId",
            data = insertModel
        )
    }
}