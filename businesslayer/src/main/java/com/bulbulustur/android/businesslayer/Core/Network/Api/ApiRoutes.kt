package com.bulbulustur.android.businesslayer.Core.Network

object ApiRoutes {

    /*
     * Production servis sunucuları
     *
     * Globalization servisi farklı sunucuda çalışır.
     * Diğer mikroservisler ana uygulama sunucusunda çalışır.
     */
    private const val APPLICATION_SERVER_HOST =
        "37.60.239.76"

    private const val GLOBALIZATION_SERVER_HOST =
        "37.60.239.76"

    /*
     * ApiClient tarafından kullanılan varsayılan adres.
     *
     * Mevcut çalışan davranış korunmuştur.
     */
    const val DEFAULT_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/"

    // -------------------------------------------------------------------------
    // Core API service roots
    // -------------------------------------------------------------------------

    const val AI_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30100/api/"

    const val BILLING_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30105/api/"

    const val BULBULUSTUR_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30110/api/"

    const val SHIPMENT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30115/api/"

    const val MARKETPLACE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30120/api/"

    const val PAYMENT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30125/api/"

    const val ADVERT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30130/api/"

    const val BANNER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30140/api/"

    const val CAMPAIGN_API_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30150/api/"

    const val COMPANY_PAGE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30155/api/"

    const val DRAUGR_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30160/api/"

    const val HANGFIRE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30175/api/"

    const val MEMBER_SERVICE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30180/api/"

    const val COMMERCE_SUPPORT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/"

    const val COMMERCE_SUPPORT_STORE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Store/"

    const val SUPPORT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/"

    const val US_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/"

    const val BRAND_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30210/api/"

    const val GLOBALIZATION_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/api/"

    const val MAIL_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30220/api/"

    const val PRODUCT_CATEGORY_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30225/api/"

    const val STATUS_BASE_URL =
        "https://status.bulbulustur.com/api/status/"

    const val B2B_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/"

    const val B2C_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/"

    // -------------------------------------------------------------------------
    // Authentication API
    // -------------------------------------------------------------------------

    const val AUTHENTICATION_BASE_URL =
        "http://37.60.239.76:30135/api/Auth/"

    // -------------------------------------------------------------------------
    // Globalization API - 30215
    // -------------------------------------------------------------------------

    const val RESOURCE_BASE_URL =
        "http://$GLOBALIZATION_SERVER_HOST:30215/api/Resource/"

    // -------------------------------------------------------------------------
    // Member API - 30180
    // -------------------------------------------------------------------------

    /*
     * Eski repository kullanımları bozulmasın diye MEMBER_BASE_URL adı
     * korunmaktadır.
     */
    const val MEMBER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30180/api/Member/"

// -------------------------------------------------------------------------
// Support API - 30190
// -------------------------------------------------------------------------

    const val FAQ_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/Faq/"

    const val TOOLTIP_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/Tooltip/"

    const val HELP_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/Help/"

    const val HELP_CONTENT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/HelpContent/"

    const val HELP_CATEGORY_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30190/api/HelpCategory/"

    // -------------------------------------------------------------------------
    // Us API - 30200
    // -------------------------------------------------------------------------

    const val US_MEDIA_ASSET_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/UsMediaAsset/"

    const val US_COMPANY_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/UsCompany/"

    const val US_SOCIALS_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/UsSocials/"

    const val US_CONTACT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/UsContact/"

    const val US_ABOUT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30200/api/UsAbout/"

    // -------------------------------------------------------------------------
    // Brand API - 30210
    // -------------------------------------------------------------------------

    /*
     * ProductBrand/GetProductBrands biçiminde action yolu gönderen mevcut
     * repository kullanımları için service root korunmuştur.
     */
    const val PRODUCT_BRAND_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30210/api/"

    const val PRODUCT_BRAND_CONTROLLER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30210/api/ProductBrand/"

    const val BRAND_PRODUCT_SECTION_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30210/api/ProductBrandSection/"

    // -------------------------------------------------------------------------
    // Product Category API - 30225
    // -------------------------------------------------------------------------

    const val PRODUCT_CATEGORY_PRODUCT_CATEGORIES_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30225/api/ProductCategories/"

    const val PRODUCT_SPECIAL_GROUP_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30225/api/ProductSpecialGroup/"

    // -------------------------------------------------------------------------
    // Campaign API - 30150
    // -------------------------------------------------------------------------

    const val CAMPAIGN_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30150/api/Campaign/"

    const val CAMPAIGN_PRODUCT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30150/api/CampaignProduct/"

    const val DEALS_OF_DAY_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30150/api/DealsOfTheDay/"

    // -------------------------------------------------------------------------
    // Advert API - 30130
    // -------------------------------------------------------------------------

    const val ADVERT_CONTROLLER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30130/api/Advert/"

    const val ADVERT_PRODUCT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30130/api/AdvertProduct/"

    const val ADVERT_TYPE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30130/api/AdvertType/"

    // -------------------------------------------------------------------------
    // Banner API - 30140
    // -------------------------------------------------------------------------

    const val BANNER_CONTROLLER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30140/api/Banner/"

    // -------------------------------------------------------------------------
    // Company Page API - 30155
    // -------------------------------------------------------------------------

    const val COMPANY_PAGE_PRODUCT_SPECIAL_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30155/api/ProductSpecial/"

    const val COMPANY_PAGE_PRODUCT_SPECIAL_GROUP_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30155/api/ProductSpecialGroup/"

    // -------------------------------------------------------------------------
    // B2C API - 30240
    // -------------------------------------------------------------------------

    const val B2C_TEST_PRODUCT_IMAGE_URL =
        "https://www.bulbulustur.com/UploadedFiles/B2C/Products/2025_11/a7f0b0b8-0071-463a-b20b-fdb47cb7fcee.jpg"

    const val B2C_PRODUCT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/Product/"

    const val B2C_PRODUCT_FAVORITE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductFavorite/"

    const val B2C_PRODUCT_QUESTION_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductQuestion/"

    const val B2C_PRODUCT_BROWSING_HISTORY_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductBrowsingHistory/"

    const val B2C_PRODUCT_VARIANT_PICTURE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductVariantPicture/"

    const val B2C_PRODUCT_VARIANT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductVariant/"

    // -------------------------------------------------------------------------
    // B2B API - 30235
    // -------------------------------------------------------------------------

    const val B2B_TEST_PRODUCT_IMAGE_URL =
        "https://www.bulbulustur.com/UploadedFiles/B2B/Products/2024_1/e444e272-842e-4c0a-b3fe-f68d4c569ff2.jpg"

    const val B2B_PRODUCT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/Product/"

    const val B2B_WHOLESALE_FAVORITE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/WholesaleFavorite/"

    const val B2B_MESSAGE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/Message/"

    /*
     * RFQ ayrı bir API değildir.
     *
     * BuyerRequest, AssignedToSeller, BuyerRequestFile ve SendedOffer
     * B2B API altında çalışır.
     */
    const val BUYER_REQUEST_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/BuyerRequest/"

    const val ASSIGNED_TO_SELLER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/AssignedToSeller/"

    const val BUYER_REQUEST_FILE_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/BuyerRequestFile/"

    const val SENDED_OFFER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30235/api/SendedOffer/"

    // -------------------------------------------------------------------------
    // Commerce Support API - 30185
    // -------------------------------------------------------------------------

    const val COMMERCE_SUPPORT_ACCOUNT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Account/"

    const val COMMERCE_SUPPORT_BASKET_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Basket/"

    const val COMMERCE_SUPPORT_MEMBER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Account/"

    const val COMMERCE_SUPPORT_ORDER_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Order/"

    const val COMMERCE_SUPPORT_RETURN_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Return/"

    const val COMMERCE_SUPPORT_REVIEW_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/Review/"

    const val COMMERCE_SUPPORT_PRODUCT_COMPLAINT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30185/api/ProductComplaint/"

    const val B2C_PRODUCT_LOW_PRICE_REPORT_BASE_URL =
        "http://$APPLICATION_SERVER_HOST:30240/api/ProductLowPriceReport/"
}
