package com.bulbulustur.android.businesslayer.Core.Network

object ApiRoutes {

    private const val LEGACY_SERVER_HOST =
        "37.60.239.76"

    private const val GLOBALIZATION_SERVER_HOST =
        "77.92.144.197"

    const val DEFAULT_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/"

    const val GLOBALIZATION_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/api/"

    const val AUTHENTICATION_BASE_URL =
        "https://authentication.bulbulustur.com/api/Auth/"

    const val MEMBER_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30180/api/Member/"

    const val MAIL_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30220/api/"

    const val RESOURCE_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/api/Resource/"

    const val US_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30200/api/"

    const val US_MEDIA_ASSET_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30200/api/UsMediaAsset/"

    const val US_COMPANY_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30200/api/UsCompany/"

    const val TOOLTIP_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30190/api/Tooltip/"

    const val HELP_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30190/api/Help/"

    const val HELP_CONTENT_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30190/api/HelpContent/"

    const val HELP_CATEGORY_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30190/api/HelpCategory/"

    const val SENDED_OFFER_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30185/api/SendedOffer/"

    const val BUYER_REQUEST_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30185/api/BuyerRequest/"

    const val PRODUCT_CATEGORY_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30225/api/"

    const val PRODUCT_SPECIAL_GROUP_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30225/api/ProductSpecialGroup/"

    const val PRODUCT_BRAND_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30210/api/"

    const val BANNER_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30140/api/"

    const val ADVERT_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30130/api/"

    const val CAMPAIGN_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30150/api/Campaign/"

    const val DEALS_OF_DAY_BASE_URL =
        "http://$LEGACY_SERVER_HOST:30150/api/DealsOfTheDay/"
}