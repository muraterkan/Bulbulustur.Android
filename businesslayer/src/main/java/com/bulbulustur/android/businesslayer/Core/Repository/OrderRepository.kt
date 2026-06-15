package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class OrderRepository(
    private val apiClient: ApiClient
) : IOrderRepository {

    override suspend fun GetOrderListAsync(): Result<List<OrderDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetOrderByIdAsync(
        orderId: Int
    ): Result<OrderUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetOrderByIdExtendedAsync(
        orderId: Int
    ): Result<OrderDTO?> {
        TODO("Not implemented yet")
    }
}
