package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.OrderCancelationDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderCancelationUpdateModel

interface IOrderCancelationRepository {

    suspend fun GetOrderCancelationListAsync(): Result<List<OrderCancelationDTO>>

    suspend fun GetOrderCancelationByIdAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationUpdateModel?>

    suspend fun GetOrderCancelationByIdExtendedAsync(
        orderCancelationId: Int
    ): Result<OrderCancelationDTO?>
}
