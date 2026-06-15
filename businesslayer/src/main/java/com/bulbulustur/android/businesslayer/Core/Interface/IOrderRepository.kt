package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.OrderUpdateModel

interface IOrderRepository {

    suspend fun GetOrderListAsync(): Result<List<OrderDTO>>

    suspend fun GetOrderByIdAsync(
        orderId: Int
    ): Result<OrderUpdateModel?>

    suspend fun GetOrderByIdExtendedAsync(
        orderId: Int
    ): Result<OrderDTO?>
}
