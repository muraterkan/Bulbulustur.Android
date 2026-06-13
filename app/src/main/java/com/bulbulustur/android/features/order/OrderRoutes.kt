package com.bulbulustur.android.features.order

import android.net.Uri

object OrderRoutes {

    const val List = "order/list"

    const val Detail = "order/detail/{orderId}"
    const val Contract = "order/contract/{orderKey}/{storeKey}"

    const val CancelRequest = "order/cancel-request/{orderStoreLineId}/{orderKey}"
    const val ReturnRequest = "order/return-request/{orderStoreLineId}/{orderKey}"
    const val ReviewCreate = "order/review-create/{orderStoreLineId}/{productId}/{memberKey}"
    const val ShipmentTracking = "order/shipment-tracking/{orderStoreLineId}"

    const val ArgOrderId = "orderId"
    const val ArgOrderKey = "orderKey"
    const val ArgStoreKey = "storeKey"
    const val ArgOrderStoreLineId = "orderStoreLineId"
    const val ArgProductId = "productId"
    const val ArgMemberKey = "memberKey"

    fun detail(
        orderId: Int
    ): String {
        return "order/detail/$orderId"
    }

    fun contract(
        orderKey: String,
        storeKey: String
    ): String {
        return "order/contract/${orderKey.safeRouteValue()}/${storeKey.safeRouteValue()}"
    }

    fun cancelRequest(
        orderStoreLineId: Long,
        orderKey: String
    ): String {
        return "order/cancel-request/$orderStoreLineId/${orderKey.safeRouteValue()}"
    }

    fun returnRequest(
        orderStoreLineId: Long,
        orderKey: String
    ): String {
        return "order/return-request/$orderStoreLineId/${orderKey.safeRouteValue()}"
    }

    fun reviewCreate(
        orderStoreLineId: Long,
        productId: Long,
        memberKey: String
    ): String {
        return "order/review-create/$orderStoreLineId/$productId/${memberKey.safeRouteValue()}"
    }

    fun shipmentTracking(
        orderStoreLineId: Long
    ): String {
        return "order/shipment-tracking/$orderStoreLineId"
    }

    private fun String.safeRouteValue(): String {
        return Uri.encode(this)
    }
}