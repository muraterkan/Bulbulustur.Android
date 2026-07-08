package com.bulbulustur.android.Application.Navigation.Routes

object SplashRoutes {
    const val Landing = "splash/landing"
    const val ModeSelection = "splash/mode-selection"
}

object RetailRoutes {

    const val ArgProductId =
        "productId"

    const val ArgStoreId =
        "storeId"

    const val ArgVariantId =
        "variantId"

    const val ArgCategoryId =
        "categoryId"

    const val Home =
        "retail/home"

    const val CategoryHome =
        "retail/category-home"

    const val CategoryDetail =
        "retail/category-detail/{$ArgCategoryId}"

    fun categoryDetail(categoryId: Int): String {
        return "retail/category-detail/$categoryId"
    }

    const val ProductList =
        "retail/product-list?$ArgCategoryId={$ArgCategoryId}"

    fun productList(categoryId: Int = 0): String {
        return "retail/product-list?$ArgCategoryId=$categoryId"
    }

    const val ProductDetail =
        "retail/product-detail/{$ArgProductId}/{$ArgStoreId}/{$ArgVariantId}"

    const val Search =
        "retail/search"

    const val Menu =
        "retail/menu"

    const val ProductReview =
        "retail/product-review/{$ArgProductId}/{$ArgStoreId}/{$ArgVariantId}"

    const val ProductQuestion =
        "retail/product-question/{$ArgProductId}/{$ArgStoreId}/{$ArgVariantId}"

    const val OtherSellerList =
        "retail/other-seller-list"

    const val CampaignList =
        "retail/campaign-list"

    const val CampaignDetail =
        "retail/campaign-detail"

    fun productDetail(productId: Int, storeId: Int, variantId: Int): String {
        return "retail/product-detail/$productId/$storeId/$variantId"
    }

    fun productReview(productId: Int, storeId: Int, variantId: Int): String {
        return "retail/product-review/$productId/$storeId/$variantId"
    }

    fun productQuestion(productId: Int, storeId: Int, variantId: Int): String {
        return "retail/product-question/$productId/$storeId/$variantId"
    }
}

object BasketRoutes {
    const val Basket = "basket"
    const val Checkout = "basket/checkout"
}

object StoreRoutes {

    const val ArgStoreId = "storeId"

    const val StoreList = "store/list"

    const val StoreDetail =
        "store/detail/{$ArgStoreId}"

    const val StoreProductList = "store/product-list"
    const val StoreOnboardingInfo = "b2c/store/onboarding-info"
    const val StoreLanding = "store/landing"

    fun storeDetail(storeId: Int): String {
        return "store/detail/$storeId"
    }
}

object WholesaleRoutes {

    const val ArgCategoryId = "categoryId"
    const val ArgProductId = "productId"

    const val Home = "wholesale/home"
    const val CategoryHome = "wholesale/category-home"

    const val CategoryDetail =
        "wholesale/category-detail/{$ArgCategoryId}"

    const val ProductList = "wholesale/product-list?$ArgCategoryId={$ArgCategoryId}"

    fun productList(categoryId: Int = 0): String {
        return "wholesale/product-list?$ArgCategoryId=$categoryId"
    }

    const val ProductDetail =
        "wholesale/product-detail"

    const val ProductDetailRoute =
        "$ProductDetail?$ArgProductId={$ArgProductId}"

    const val Search = "wholesale/search"
    const val Menu = "wholesale/menu"

    const val QuotationRequests = "wholesale/rfq/list"
    const val RfqCreate = "wholesale/rfq/create"

    const val LastPriceRequest =
        "wholesale/last-price-request"

    const val SampleRequest =
        "wholesale/sample-request"

    const val CustomizationRequest =
        "wholesale/customization-request"

    fun categoryDetail(categoryId: Int): String {
        return "wholesale/category-detail/$categoryId"
    }

    fun productDetail(productId: Int): String {
        return "$ProductDetail?$ArgProductId=$productId"
    }
}

object RfqRoutes {

    const val List = "wholesale/rfq"
    const val Create = "wholesale/rfq/create"

    const val ArgBuyerRequestKey = "buyerRequestKey"
    const val ArgSendedOfferId = "sendedOfferId"

    const val Detail = "wholesale/rfq/detail/{$ArgBuyerRequestKey}"
    const val Offers = "wholesale/rfq/offers/{$ArgBuyerRequestKey}"
    const val OfferDetail = "wholesale/rfq/offer/{$ArgSendedOfferId}"

    fun detail(buyerRequestKey: String): String = "wholesale/rfq/detail/$buyerRequestKey"

    fun offers(buyerRequestKey: String): String = "wholesale/rfq/offers/$buyerRequestKey"

    fun offerDetail(sendedOfferId: Int): String = "wholesale/rfq/offer/$sendedOfferId"
}

object OrderRoutes {
    const val List = "order/list"
    const val Detail = "order/detail/{orderId}/{orderKey}"
    const val Contract = "order/contract/{orderKey}/{storeKey}"
    const val CancelRequest = "order/cancel-request/{orderStoreLineId}/{orderKey}"
    const val ReturnRequest = "order/return-request/{orderStoreLineId}/{orderKey}"
    const val ReviewCreate = "order/review-create/{orderStoreLineId}/{productId}/{productSecureKey}"
    const val ShipmentTracking = "order/shipment-tracking/{cargoTrackingNumber}"

    const val ArgOrderId = "orderId"
    const val ArgOrderKey = "orderKey"
    const val ArgStoreKey = "storeKey"
    const val ArgOrderStoreLineId = "orderStoreLineId"
    const val ArgProductId = "productId"
    const val ArgProductSecureKey = "productSecureKey"
    const val ArgCargoTrackingNumber = "cargoTrackingNumber"

    fun detail(orderId: Int, orderKey: String): String {
        return "order/detail/$orderId/$orderKey"
    }

    fun contract(orderKey: String, storeKey: String): String {
        return "order/contract/$orderKey/$storeKey"
    }

    fun cancelRequest(orderStoreLineId: Long, orderKey: String): String {
        return "order/cancel-request/$orderStoreLineId/$orderKey"
    }

    fun returnRequest(orderStoreLineId: Long, orderKey: String): String {
        return "order/return-request/$orderStoreLineId/$orderKey"
    }

    fun reviewCreate(orderStoreLineId: Long, productId: Long, productSecureKey: String): String {
        return "order/review-create/$orderStoreLineId/$productId/$productSecureKey"
    }

    fun shipmentTracking(cargoTrackingNumber: Int): String {
        return "order/shipment-tracking/$cargoTrackingNumber"
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
    const val PhoneVerify = "account/phones/verify/{memberPhoneId}"

    fun PhoneVerify(memberPhoneId: Int): String {
        return "account/phones/verify/$memberPhoneId"
    }

    const val AddressList = "account/address"
    const val AddressCreate = "account/address/create"
    const val AddressEdit = "account/address/edit/{addressKey}"

    fun editAddress(addressKey: String): String {
        return "account/address/edit/$addressKey"
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
    const val RequestDetail = "account/request-detail/{requestId}"

    fun requestDetail(requestId: Int): String {
        return "account/request-detail/$requestId"
    }

    const val Subscriptions = "account/subscriptions"
    const val SubscriptionDetail = "account/subscriptions/detail/{memberSubscriptionId}"

    fun subscriptionDetail(memberSubscriptionId: Int): String {
        return "account/subscriptions/detail/$memberSubscriptionId"
    }
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
    const val SystemStatus = "settings/system-status"
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
    const val Detail = "message/detail/{messageThreadId}/{messageId}"
    const val ArgMessageThreadId = "messageThreadId"
    const val ArgMessageId = "messageId"

    fun detail(messageThreadId: Int, messageId: Int): String {
        return "message/detail/$messageThreadId/$messageId"
    }
}