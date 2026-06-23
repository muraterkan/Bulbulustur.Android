package com.bulbulustur.android.Application.Navigation.Routes

object SplashRoutes {
    const val Landing = "splash/landing"
    const val ModeSelection = "splash/mode-selection"
}

object RetailRoutes {
    const val Home = "retail/home"
    const val CategoryHome = "retail/category-home"
    const val CategoryDetail = "retail/category-detail"
    const val ProductList = "retail/product-list"
    const val ProductDetail = "retail/product-detail"
    const val Search = "retail/search"
    const val Menu = "retail/menu"

    const val ProductReview = "retail/product-review"
    const val ProductQuestion = "retail/product-question"
    const val OtherSellerList = "retail/other-seller-list"

    const val CampaignList = "retail/campaign-list"
    const val CampaignDetail = "retail/campaign-detail"
}

object BasketRoutes {
    const val Basket = "basket"
    const val Checkout = "basket/checkout"
}

object StoreRoutes {
    const val StoreList = "store/list"
    const val StoreDetail = "store/detail"
    const val StoreProductList = "store/product-list"
    const val StoreLanding = "store/landing"
}

object WholesaleRoutes {
    const val Home = "wholesale/home"
    const val CategoryHome = "wholesale/category-home"
    const val CategoryDetail = "wholesale/category-detail"
    const val ProductList = "wholesale/product-list"
    const val ProductDetail = "wholesale/product-detail"
    const val Search = "wholesale/search"
    const val Menu = "wholesale/menu"

    const val QuotationRequests = "wholesale/rfq/list"
    const val RfqCreate = "wholesale/rfq/create"

    const val LastPriceRequest = "wholesale/last-price-request"
    const val SampleRequest = "wholesale/sample-request"
    const val CustomizationRequest = "wholesale/customization-request"
}

object RfqRoutes {
    const val List = "wholesale/rfq/list"
    const val Create = "wholesale/rfq/create"
    const val Detail = "wholesale/rfq/detail/{buyerRequestId}"
    const val OfferDetail = "wholesale/rfq/offer-detail/{buyerRequestId}/{sendedOfferId}"

    const val ArgBuyerRequestId = "buyerRequestId"
    const val ArgSendedOfferId = "sendedOfferId"

    fun detail(buyerRequestId: Int): String {
        return "wholesale/rfq/detail/$buyerRequestId"
    }

    fun offerDetail(
        buyerRequestId: Int,
        sendedOfferId: Int
    ): String {
        return "wholesale/rfq/offer-detail/$buyerRequestId/$sendedOfferId"
    }
}

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

    fun detail(orderId: Int): String {
        return "order/detail/$orderId"
    }

    fun contract(
        orderKey: String,
        storeKey: String
    ): String {
        return "order/contract/$orderKey/$storeKey"
    }

    fun cancelRequest(
        orderStoreLineId: Long,
        orderKey: String
    ): String {
        return "order/cancel-request/$orderStoreLineId/$orderKey"
    }

    fun returnRequest(
        orderStoreLineId: Long,
        orderKey: String
    ): String {
        return "order/return-request/$orderStoreLineId/$orderKey"
    }

    fun reviewCreate(
        orderStoreLineId: Long,
        productId: Long,
        memberKey: String
    ): String {
        return "order/review-create/$orderStoreLineId/$productId/$memberKey"
    }

    fun shipmentTracking(
        orderStoreLineId: Long
    ): String {
        return "order/shipment-tracking/$orderStoreLineId"
    }
}

object AccountRoutes {
    const val AccountHome = "account"

    const val Security = "account/security"
    const val ProfileInfo = "account/profile-info"
    const val ProfileEdit = "account/profile/edit"

    const val EmailChange = "account/security/email"
    const val PasswordChange = "account/security/password"
    const val LoginActivities = "account/security/login-activities"

    const val PhoneList = "account/phones"
    const val PhoneCreate = "account/phones/create"
    const val PhoneVerify = "account/phones/verify"

    const val AddressList = "account/address"
    const val AddressCreate = "account/address/create"
    const val AddressEdit = "account/address/edit/{addressId}"

    fun editAddress(addressId: Int): String {
        return "account/address/edit/$addressId"
    }

    const val Notifications = "account/notifications"

    const val CompanyInfo = "account/company"
    const val CompanyInfoEdit = "account/company/edit"
    const val CompanyB2BIndex = "account/company/b2b-index"
    const val CompanyB2BStatus = "account/company/b2b-status"

    const val UsagePurpose = "account/usage-purpose"
    const val QuestionAnswers = "account/question-answers"

    const val FollowedStores = "account/followed-stores"
    const val QuotationRequests = "account/quotations"

    const val Orders = "account/orders"
    const val OrderDetail = "account/orders/detail"
    const val OrderContract = "account/orders/contract"

    const val Favorites = "account/favorites"
    const val Reviews = "account/reviews"
    const val ReviewEdit = "account/reviews/edit"
    const val Coupons = "account/coupons"
    const val Requests = "account/requests"
    const val RequestDetail = "account/requests/detail"
    const val Subscriptions = "account/subscriptions"
    const val SubscriptionDetail = "account/subscriptions/detail"
    const val WalletBalance = "account/wallet-balance"
}

object BankAccountRoutes {
    const val List = "account/bank-accounts"
    const val Create = "account/bank-accounts/create"
    const val Edit = "account/bank-accounts/edit/{bankAccountId}"

    fun edit(bankAccountId: Int): String {
        return "account/bank-accounts/edit/$bankAccountId"
    }
}

object SettingsRoutes {
    const val Home = "settings/home"
    const val Language = "settings/language"
    const val Appearance = "settings/appearance"
    const val Communication = "settings/communication"
    const val Region = "settings/region"
    const val Currency = "settings/currency"
    const val LegalPolicies = "settings/legal-policies"
    const val AboutThisApp = "settings/about-this-app"

    const val LegalPolicyDetail = "settings/legal-policy-detail/{policyKey}"

    fun legalPolicyDetail(policyKey: String): String {
        return "settings/legal-policy-detail/$policyKey"
    }
}

object CompanyRoutes {
    const val CompanyHome = "company/home"
    const val CompanyList = "company/list"
    const val CompanyDetail = "company/detail"
    const val CompanyProducts = "company/products"
    const val CompanyContact = "company/contact"

    const val CompanyEdit = "company/edit"
    const val CompanyActivate = "company/activate"
}

object MessageRoutes {
    const val Inbox = "message/inbox"

    const val Detail = "message/detail/{messageId}"
    const val ArgMessageId = "messageId"

    fun detail(messageId: Int): String {
        return "message/detail/$messageId"
    }
}
