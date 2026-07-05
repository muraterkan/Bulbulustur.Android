package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.OrderDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreDTO
import com.bulbulustur.android.businesslayer.Core.DTO.OrderStoreLineDTO
import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.OrderCancelationInsertModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IOrderRepository {

    suspend fun GetOrdersByMemberIdAsync(memberId: Int, count: Int = 100): Result<List<OrderDTO>>

    suspend fun GetOrderStoresAsync(orderKey: String): Result<List<OrderStoreDTO>>

    suspend fun GetOrderTrackingAsync(cargoTrackingNumber: Int, memberId: Int): Result<OrderStoreLineDTO?>

    suspend fun GetOrderCancelationTypes(count: Int = 15): Result<List<SystemDescOrderCancelationTypeDTO>>

    suspend fun InsertOrderCancelationAsync(languageId: Int, memberId: Int, insertModel: OrderCancelationInsertModel): Result<Unit>
}