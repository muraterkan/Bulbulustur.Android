package com.bulbulustur.android.features.account

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
    const val Coupons = "account/coupons"
    const val Requests = "account/requests"
    const val RequestDetail = "account/requests/detail"
    const val Subscriptions = "account/subscriptions"

}