package com.bulbulustur.android.Features.areas.b2b.rfq

object RfqRoutes {

    const val List = "wholesale/rfq/list"
    const val Create = "wholesale/rfq/create"
    const val Detail = "wholesale/rfq/detail/{buyerRequestId}"
    const val OfferDetail = "wholesale/rfq/offer-detail/{buyerRequestId}/{sendedOfferId}"

    const val ArgBuyerRequestId = "buyerRequestId"
    const val ArgSendedOfferId = "sendedOfferId"

    fun detail(
        buyerRequestId: Int
    ): String {
        return "wholesale/rfq/detail/$buyerRequestId"
    }

    fun offerDetail(
        buyerRequestId: Int,
        sendedOfferId: Int
    ): String {
        return "wholesale/rfq/offer-detail/$buyerRequestId/$sendedOfferId"
    }
}