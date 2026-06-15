package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.OrderCancelationDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IOrderCancelationRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderCancelationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class OrderCancelationRepository(
    private val apiClient: ApiClient
) : IOrderCancelationRepository {

    override suspend fun GetOrderCancelationListAsync(): Result<List<OrderCancelationDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetOrderCancelationByIdAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetOrderCancelationByIdExtendedAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationDTO?> {
        TODO("Not implemented yet")
    }
}
