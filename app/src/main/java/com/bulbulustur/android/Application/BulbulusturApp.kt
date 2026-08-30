package com.bulbulustur.android.Application

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.HomeController as WholesaleHomeController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.ProductController as WholesaleProductController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.SearchController as WholesaleSearchController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.RfqController
import com.bulbulustur.android.Application.Areas.b2b.Controllers.WholesaleBuyerRequestController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.BasketController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.CampaignController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.DealsOfTheDayController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.HomeController as RetailHomeController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductController as RetailProductController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.SearchController as RetailSearchController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductQuestionController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.ProductReviewController
import com.bulbulustur.android.Application.Areas.b2c.Controllers.StoreController
import com.bulbulustur.android.Application.Controllers.AccountController
import com.bulbulustur.android.Application.Controllers.CompanyController
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Controllers.MessageController
import com.bulbulustur.android.Application.Controllers.ProfileController
import com.bulbulustur.android.Application.Controllers.SettingsController
import com.bulbulustur.android.Application.Datastore.ProductCategoryDataStore
import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.Application.Localization.BBLocalizationProvider
import com.bulbulustur.android.Application.Localization.LocalizationManager
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Graph.accountGraph
import com.bulbulustur.android.Application.Navigation.Graph.companyGraph
import com.bulbulustur.android.Application.Navigation.Graph.logonGraph
import com.bulbulustur.android.Application.Navigation.Graph.messageGraph
import com.bulbulustur.android.Application.Navigation.Graph.orderGraph
import com.bulbulustur.android.Application.Navigation.Graph.profileGraph
import com.bulbulustur.android.Application.Navigation.Graph.retailGraph
import com.bulbulustur.android.Application.Navigation.Graph.settingsGraph
import com.bulbulustur.android.Application.Navigation.Graph.splashGraph
import com.bulbulustur.android.Application.Navigation.Graph.wholesaleGraph
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeController
import com.bulbulustur.android.Application.Views.Shared.Components.BuyerModeSheet
import com.bulbulustur.android.Application.wwwroot.Theme.BbTheme
import com.bulbulustur.android.businesslayer.Core.Enums.EBuyerMode
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCityRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCountryDepartmentRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCountryRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AddressCountryStateRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AddressDistrictRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AdvertSponsoredRepository
import com.bulbulustur.android.businesslayer.Core.Repository.AuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Repository.BasketRepository
import com.bulbulustur.android.businesslayer.Core.Repository.BuyerRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.CampaignRepository
import com.bulbulustur.android.businesslayer.Core.Repository.CompanyRepository
import com.bulbulustur.android.businesslayer.Core.Repository.DealsOfTheDayRepository
import com.bulbulustur.android.businesslayer.Core.Repository.LocalizationRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberAddressRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberAgreementRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberAlarmListRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberBankAccountRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberCouponRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberFollowedCompanyRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberFollowedStoreRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberLoginActivityRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberPhoneRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberPreferenceRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberSubscriptionRepository
import com.bulbulustur.android.businesslayer.Core.Repository.MemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductBrandSectionRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductBrowsingHistoryRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductCategoryRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductComplaintRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductCustomerQuestionRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductHomepageSpecialContentRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductLowPriceReportRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductVariantPictureRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ProductVariantRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ReturnRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.ReviewRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SendedOfferRepository
import com.bulbulustur.android.businesslayer.Core.Repository.StatusRepository
import com.bulbulustur.android.businesslayer.Core.Repository.StoreRepository
import com.bulbulustur.android.businesslayer.Core.Repository.StoreRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescColorRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescCurrencyRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescGenderRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescLanguageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescMaterialTypeRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescPaymentTermRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescTradeTermRepository
import com.bulbulustur.android.businesslayer.Core.Repository.SystemDescUnitRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleBuyerCustomizeRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleBuyerLastPriceRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleBuyerSampleRequestRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleFavoriteRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleHomepageFeaturedProductRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleHomepageSpecialContentRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleMessageRepository
import com.bulbulustur.android.businesslayer.Core.Repository.WholesaleProductRepository
import com.bulbulustur.android.businesslayer.Core.Security.SecureTokenStore
import com.bulbulustur.android.businesslayer.Core.Util.Execute.ExecuteService

@Composable
fun BulbulusturApp(
    appLinkUrl: String? = null,
    onAppLinkConsumed: () -> Unit = {}
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val userPreferenceDataStore = remember(context) {
        UserPreferenceDataStore(context = context.applicationContext)
    }

    val secureTokenStore = remember(context) {
        SecureTokenStore(context = context.applicationContext)
    }

    val userSessionManager = remember(userPreferenceDataStore, secureTokenStore, coroutineScope) {
        UserSessionManager(
            userPreferenceDataStore = userPreferenceDataStore,
            secureTokenStore = secureTokenStore,
            coroutineScope = coroutineScope
        )
    }

    val localizationRepository = remember {
        LocalizationRepository()
    }

    val localizationManager = remember(localizationRepository, coroutineScope) {
        LocalizationManager(
            localizationRepository = localizationRepository,
            coroutineScope = coroutineScope
        )
    }

    val sessionState by userSessionManager.State.collectAsState()
    val localizationState by localizationManager.State.collectAsState()

    LaunchedEffect(
        sessionState.IsInitialized,
        sessionState.Language.Id,
        sessionState.Language.Code
    ) {
        if (sessionState.IsInitialized) {
            localizationManager.Load(
                languageId = sessionState.Language.Id,
                languageCode = sessionState.Language.Code
            )
        }
    }

    BbTheme(
        themeMode = sessionState.ThemeMode
    ) {
        BBLocalizationProvider(
            state = localizationState
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                if (
                    sessionState.IsInitialized &&
                    localizationState.IsInitialized &&
                    !sessionState.IsAuthenticationInitializing
                ) {
                    BulbulusturApplicationContent(
                        sessionState = sessionState,
                        userSessionManager = userSessionManager,
                        appLinkUrl = appLinkUrl,
                        onAppLinkConsumed = onAppLinkConsumed
                    )
                }
            }
        }
    }
}

@Composable
private fun BulbulusturApplicationContent(
    sessionState: UserSessionState,
    userSessionManager: UserSessionManager,
    appLinkUrl: String?,
    onAppLinkConsumed: () -> Unit
) {
    val context = LocalContext.current

    val productCategoryDataStore = remember(context) {
        ProductCategoryDataStore(context.applicationContext)
    }

    val navController = rememberNavController()

    var showBuyerModeSheet by remember {
        mutableStateOf(false)
    }

    val executeService = remember {
        ExecuteService()
    }

    val systemDescUnitRepository = remember {
        SystemDescUnitRepository()
    }

    val systemDescGenderRepository = remember {
        SystemDescGenderRepository()
    }

    val systemDescColorRepository = remember {
        SystemDescColorRepository()
    }

    val systemDescMaterialTypeRepository = remember {
        SystemDescMaterialTypeRepository()
    }

    val systemDescPaymentTermRepository = remember {
        SystemDescPaymentTermRepository()
    }

    val systemDescTradeTermRepository = remember {
        SystemDescTradeTermRepository()
    }

    val authenticationRepository = remember {
        AuthenticationRepository()
    }

    val memberSubscriptionRepository = remember {
        MemberSubscriptionRepository()
    }

    val companyRepository = remember {
        CompanyRepository()
    }

    val memberTempRepository = remember {
        MemberTempRepository()
    }

    val systemDescLanguageRepository = remember {
        SystemDescLanguageRepository()
    }

    val systemDescCurrencyRepository = remember {
        SystemDescCurrencyRepository()
    }

    val addressCountryRepository = remember {
        AddressCountryRepository()
    }

    val addressCityRepository = remember {
        AddressCityRepository()
    }

    val addressCountryStateRepository = remember {
        AddressCountryStateRepository()
    }

    val addressCountryDepartmentRepository = remember {
        AddressCountryDepartmentRepository()
    }

    val addressDistrictRepository = remember {
        AddressDistrictRepository()
    }

    val memberRepository = remember {
        MemberRepository()
    }

    val memberPreferenceRepository = remember {
        MemberPreferenceRepository()
    }

    val buyerRequestRepository = remember {
        BuyerRequestRepository()
    }

    val wholesaleBuyerLastPriceRequestRepository = remember {
        WholesaleBuyerLastPriceRequestRepository()
    }

    val wholesaleBuyerSampleRequestRepository = remember {
        WholesaleBuyerSampleRequestRepository()
    }

    val wholesaleBuyerCustomizeRequestRepository = remember {
        WholesaleBuyerCustomizeRequestRepository()
    }

    val sendedOfferRepository = remember {
        SendedOfferRepository()
    }

    val memberAddressRepository = remember {
        MemberAddressRepository()
    }

    val memberBankAccountRepository = remember {
        MemberBankAccountRepository()
    }

    val memberAlarmListRepository = remember {
        MemberAlarmListRepository()
    }

    val memberFollowedCompanyRepository = remember {
        MemberFollowedCompanyRepository()
    }

    val memberFollowedStoreRepository = remember {
        MemberFollowedStoreRepository()
    }

    val storeRepository = remember {
        StoreRepository()
    }

    val storeController = remember(storeRepository) {
        StoreController(storeRepository = storeRepository)
    }

    val memberAgreementRepository = remember {
        MemberAgreementRepository()
    }

    val memberLoginActivityRepository = remember {
        MemberLoginActivityRepository()
    }

    val memberCouponRepository = remember {
        MemberCouponRepository()
    }

    val productFavoriteRepository = remember {
        ProductFavoriteRepository()
    }

    val wholesaleFavoriteRepository = remember {
        WholesaleFavoriteRepository()
    }

    val memberPhoneRepository = remember {
        MemberPhoneRepository()
    }

    val productRepository = remember {
        ProductRepository()
    }

    val productVariantRepository = remember {
        ProductVariantRepository()
    }

    val productVariantPictureRepository = remember {
        ProductVariantPictureRepository()
    }

    val advertSponsoredRepository = remember {
        AdvertSponsoredRepository()
    }

    val statusRepository = remember {
        StatusRepository()
    }

    val productBrandSectionRepository = remember {
        ProductBrandSectionRepository()
    }

    val productBrowsingHistoryRepository = remember {
        ProductBrowsingHistoryRepository()
    }

    val productCategoryRepository = remember {
        ProductCategoryRepository()
    }

    val storeRequestRepository = remember {
        StoreRequestRepository()
    }

    val campaignRepository = remember {
        CampaignRepository()
    }

    val campaignController = remember(
        campaignRepository,
        productRepository
    ) {
        CampaignController(
            campaignRepository = campaignRepository,
            productRepository = productRepository
        )
    }

    val productHomepageSpecialContentRepository = remember {
        ProductHomepageSpecialContentRepository()
    }

    val dealsOfTheDayRepository = remember {
        DealsOfTheDayRepository()
    }

    val dealsOfTheDayController = remember(
        dealsOfTheDayRepository,
        productRepository
    ) {
        DealsOfTheDayController(
            dealsOfTheDayRepository = dealsOfTheDayRepository,
            productRepository = productRepository
        )
    }

    val retailHomeController = remember(
        campaignRepository,
        dealsOfTheDayRepository,
        productHomepageSpecialContentRepository,
        productRepository
    ) {
        RetailHomeController(
            campaignRepository = campaignRepository,
            dealsOfTheDayRepository = dealsOfTheDayRepository,
            productHomepageSpecialContentRepository = productHomepageSpecialContentRepository,
            productRepository = productRepository
        )
    }

    val productComplaintRepository = remember {
        ProductComplaintRepository()
    }

    val productLowPriceReportRepository = remember {
        ProductLowPriceReportRepository()
    }

    val retailSearchController = remember(executeService, productRepository) {
        RetailSearchController(
            executeService = executeService,
            productRepository = productRepository
        )
    }

    val productController = remember(
        executeService,
        productRepository,
        productVariantRepository,
        productVariantPictureRepository,
        advertSponsoredRepository,
        productBrandSectionRepository,
        productBrowsingHistoryRepository,
        productCategoryRepository,
        productComplaintRepository,
        productLowPriceReportRepository
    ) {
        RetailProductController(
            executeService = executeService,
            productRepository = productRepository,
            productVariantRepository = productVariantRepository,
            productVariantPictureRepository = productVariantPictureRepository,
            advertSponsoredRepository = advertSponsoredRepository,
            productBrandSectionRepository = productBrandSectionRepository,
            productBrowsingHistoryRepository = productBrowsingHistoryRepository,
            productCategoryRepository = productCategoryRepository,
            productComplaintRepository = productComplaintRepository,
            productLowPriceReportRepository = productLowPriceReportRepository
        )
    }

    val wholesaleProductRepository = remember {
        WholesaleProductRepository()
    }

    val wholesaleSearchController = remember(executeService, wholesaleProductRepository) {
        WholesaleSearchController(
            executeService = executeService,
            wholesaleProductRepository = wholesaleProductRepository
        )
    }

    val wholesaleProductController = remember(
        executeService,
        wholesaleProductRepository,
        productCategoryRepository
    ) {
        WholesaleProductController(
            executeService = executeService,
            wholesaleProductRepository = wholesaleProductRepository,
            productCategoryRepository = productCategoryRepository
        )
    }

    val wholesaleHomepageFeaturedProductRepository = remember {
        WholesaleHomepageFeaturedProductRepository()
    }

    val wholesaleHomepageSpecialContentRepository = remember {
        WholesaleHomepageSpecialContentRepository()
    }

    val wholesaleHomeController = remember(
        wholesaleHomepageFeaturedProductRepository,
        wholesaleHomepageSpecialContentRepository
    ) {
        WholesaleHomeController(
            wholesaleHomepageFeaturedProductRepository = wholesaleHomepageFeaturedProductRepository,
            wholesaleHomepageSpecialContentRepository = wholesaleHomepageSpecialContentRepository
        )
    }

    val returnRequestRepository = remember {
        ReturnRequestRepository()
    }

    val reviewRepository = remember {
        ReviewRepository()
    }

    val productReviewController = remember(
        executeService,
        reviewRepository
    ) {
        ProductReviewController(
            executeService = executeService,
            reviewRepository = reviewRepository
        )
    }

    val productCustomerQuestionRepository = remember {
        ProductCustomerQuestionRepository()
    }

    val productQuestionController = remember(
        executeService,
        productCustomerQuestionRepository
    ) {
        ProductQuestionController(
            executeService = executeService,
            productCustomerQuestionRepository = productCustomerQuestionRepository
        )
    }

    val basketRepository = remember {
        BasketRepository()
    }

    val basketController = remember(
        executeService,
        basketRepository
    ) {
        BasketController(
            executeService = executeService,
            basketRepository = basketRepository
        )
    }

    val wholesaleMessageRepository = remember {
        WholesaleMessageRepository()
    }

    val messageController = remember(
        executeService,
        wholesaleMessageRepository
    ) {
        MessageController(
            executeService = executeService,
            wholesaleMessageRepository = wholesaleMessageRepository
        )
    }

    val addressCascadeController = remember(
        executeService,
        addressCountryRepository,
        addressCountryStateRepository,
        addressCountryDepartmentRepository,
        addressCityRepository,
        addressDistrictRepository
    ) {
        AddressCascadeController(
            executeService = executeService,
            addressCountryRepository = addressCountryRepository,
            addressCountryStateRepository = addressCountryStateRepository,
            addressCountryDepartmentRepository = addressCountryDepartmentRepository,
            addressCityRepository = addressCityRepository,
            addressDistrictRepository = addressDistrictRepository
        )
    }

    val logonController = remember(
        executeService,
        authenticationRepository,
        memberTempRepository,
        memberRepository,
        userSessionManager
    ) {
        LogonController(
            executeService = executeService,
            authenticationRepository = authenticationRepository,
            memberTempRepository = memberTempRepository,
            memberRepository = memberRepository,
            userSessionManager = userSessionManager
        )
    }

    val wholesaleBuyerRequestController = remember(
        executeService,
        wholesaleBuyerLastPriceRequestRepository,
        wholesaleBuyerSampleRequestRepository,
        wholesaleBuyerCustomizeRequestRepository
    ) {
        WholesaleBuyerRequestController(
            executeService = executeService,
            lastPriceRequestRepository = wholesaleBuyerLastPriceRequestRepository,
            sampleRequestRepository = wholesaleBuyerSampleRequestRepository,
            customizeRequestRepository = wholesaleBuyerCustomizeRequestRepository
        )
    }

    val profileController = remember(
        executeService,
        memberRepository,
        systemDescGenderRepository
    ) {
        ProfileController(
            executeService = executeService,
            memberRepository = memberRepository,
            systemDescGenderRepository = systemDescGenderRepository
        )
    }

    val accountController = remember(
        executeService,
        systemDescGenderRepository,
        memberRepository,
        memberAddressRepository,
        memberBankAccountRepository,
        memberAlarmListRepository,
        memberFollowedCompanyRepository,
        memberFollowedStoreRepository,
        memberAgreementRepository,
        memberLoginActivityRepository,
        memberCouponRepository,
        returnRequestRepository,
        reviewRepository,
        productFavoriteRepository,
        wholesaleFavoriteRepository,
        memberPhoneRepository,
        productCustomerQuestionRepository,
        memberSubscriptionRepository,
        companyRepository,
        storeRequestRepository
    ) {
        AccountController(
            executeService = executeService,
            memberRepository = memberRepository,
            memberAddressRepository = memberAddressRepository,
            memberBankAccountRepository = memberBankAccountRepository,
            memberAlarmListRepository = memberAlarmListRepository,
            memberFollowedCompanyRepository = memberFollowedCompanyRepository,
            memberFollowedStoreRepository = memberFollowedStoreRepository,
            memberAgreementRepository = memberAgreementRepository,
            memberLoginActivityRepository = memberLoginActivityRepository,
            memberPreferenceRepository = memberPreferenceRepository,
            memberCouponRepository = memberCouponRepository,
            returnRequestRepository = returnRequestRepository,
            reviewRepository = reviewRepository,
            productFavoriteRepository = productFavoriteRepository,
            wholesaleFavoriteRepository = wholesaleFavoriteRepository,
            memberPhoneRepository = memberPhoneRepository,
            productCustomerQuestionRepository = productCustomerQuestionRepository,
            memberSubscriptionRepository = memberSubscriptionRepository,
            companyRepository = companyRepository,
            storeRequestRepository = storeRequestRepository
        )
    }

    val assignedToSellerRepository = remember {
        com.bulbulustur.android.businesslayer.Core.Repository.AssignedToSellerRepository()
    }

    val rfqController = remember(
        executeService,
        buyerRequestRepository,
        assignedToSellerRepository,
        sendedOfferRepository,
        productCategoryRepository,
        systemDescUnitRepository,
        systemDescCurrencyRepository,
        systemDescColorRepository,
        systemDescMaterialTypeRepository,
        systemDescPaymentTermRepository,
        systemDescTradeTermRepository
    ) {
        RfqController(
            executeService = executeService,
            buyerRequestRepository = buyerRequestRepository,
            assignedToSellerRepository = assignedToSellerRepository,
            sendedOfferRepository = sendedOfferRepository,
            productCategoryRepository = productCategoryRepository,
            systemDescUnitRepository = systemDescUnitRepository,
            systemDescCurrencyRepository = systemDescCurrencyRepository,
            systemDescColorRepository = systemDescColorRepository,
            systemDescMaterialTypeRepository = systemDescMaterialTypeRepository,
            systemDescPaymentTermRepository = systemDescPaymentTermRepository,
            systemDescTradeTermRepository = systemDescTradeTermRepository
        )
    }

    val settingsController = remember(
        executeService,
        systemDescLanguageRepository,
        addressCountryRepository,
        systemDescCurrencyRepository
    ) {
        SettingsController(
            executeService = executeService,
            systemDescLanguageRepository = systemDescLanguageRepository,
            addressCountryRepository = addressCountryRepository,
            systemDescCurrencyRepository = systemDescCurrencyRepository,
            statusRepository = statusRepository
        )
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val currentBuyerMode = when {
        currentRoute?.startsWith("wholesale/") == true -> EBuyerMode.Wholesale
        else -> EBuyerMode.Retail
    }

    val appNavigator = remember(navController) {
        BulbulusturNavigator(
            navController = navController,
            openBuyerModeSheet = {
                showBuyerModeSheet = true
            },
            closeBuyerModeSheet = {
                showBuyerModeSheet = false
            }
        )
    }

    LaunchedEffect(appLinkUrl) {
        val incomingUrl = appLinkUrl ?: return@LaunchedEffect

        val uri = runCatching {
            Uri.parse(incomingUrl)
        }.getOrNull()

        val isSupportedAppLink = uri?.scheme.equals("https", ignoreCase = true) &&
                uri?.host.equals("www.bulbulustur.com", ignoreCase = true)

        if (!isSupportedAppLink) {
            onAppLinkConsumed()
            return@LaunchedEffect
        }

        val activationCode = uri
            ?.getQueryParameter("uuid")
            ?.trim()
            .orEmpty()

        if (activationCode.isBlank()) {
            navController.navigate(LogonRoutes.Expired) {
                launchSingleTop = true
            }

            onAppLinkConsumed()
            return@LaunchedEffect
        }

        when (uri?.path?.lowercase()) {
            "/logon/register" -> {
                navController.navigate(
                    LogonRoutes.CreateRegisterActivationRoute(
                        activationCode = activationCode
                    )
                ) {
                    launchSingleTop = true
                }
            }

            "/logon/setnewpassword" -> {
                navController.navigate(
                    LogonRoutes.CreateSetNewPasswordRoute(
                        activationCode = activationCode
                    )
                ) {
                    launchSingleTop = true
                }
            }

            else -> {
                onAppLinkConsumed()
                return@LaunchedEffect
            }
        }

        onAppLinkConsumed()
    }

    val companyController = remember { com.bulbulustur.android.Application.Controllers.CompanyController() }

    NavHost(
        navController = navController,
        startDestination = SplashRoutes.ModeSelection
    ) {
        splashGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            settingsController = settingsController,
            userSessionManager = userSessionManager
        )

        logonGraph(
            navController = navController,
            sessionState = sessionState,
            logonController = logonController,
            addressCascadeController = addressCascadeController,
            settingsController = settingsController,
            userSessionManager = userSessionManager
        )

        messageGraph(
            navigator = appNavigator,
            messageController = messageController,
            sessionState = sessionState
        )

        retailGraph(
            navigator = appNavigator,

            categoryController = com.bulbulustur.android.Application.Areas.b2c.Controllers.CategoryController(
                executeService = executeService,
                productCategoryRepository = productCategoryRepository,
                productCategoryDataStore = productCategoryDataStore,
                productCategoryContentRepository = com.bulbulustur.android.businesslayer.Core.Repository.ProductCategoryContentRepository(),
                productHomepageSpecialContentRepository = productHomepageSpecialContentRepository,
                productBrandCategoryMapRepository = com.bulbulustur.android.businesslayer.Core.Repository.ProductBrandCategoryMapRepository()
            )
,
            homeController = retailHomeController,
            campaignController = campaignController,
            dealsOfTheDayController = dealsOfTheDayController,
            productController = productController,
            productReviewController = productReviewController,
            productQuestionController = productQuestionController,
            searchController = retailSearchController,
            storeController = storeController,
            accountController = accountController,
            basketController = basketController,
            sessionState = sessionState
        )

        wholesaleGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            categoryController = com.bulbulustur.android.Application.Areas.b2b.Controllers.CategoryController(
                executeService = executeService,
                productCategoryRepository = productCategoryRepository,
                productCategoryDataStore = productCategoryDataStore,
                wholesaleProductCategoryContentRepository = com.bulbulustur.android.businesslayer.Core.Repository.WholesaleProductCategoryContentRepository(),
                wholesaleHomepageSpecialContentRepository = wholesaleHomepageSpecialContentRepository,
                wholesaleProductCategorySliderRepository = com.bulbulustur.android.businesslayer.Core.Repository.WholesaleProductCategorySliderRepository(),
                wholesaleProductCategorySupplierRepository = com.bulbulustur.android.businesslayer.Core.Repository.WholesaleProductCategorySupplierRepository()
            ),
            homeController = wholesaleHomeController,
            productController = wholesaleProductController,
            searchController = wholesaleSearchController,
            rfqController = rfqController,
            messageController = messageController,
            wholesaleBuyerRequestController = wholesaleBuyerRequestController
        )

        companyGraph(
            navigator = appNavigator,
            languageId = sessionState.Language.Id,
            companyController = companyController
        )

        orderGraph(
            navigator = appNavigator,
            memberId = sessionState.MemberId,
            languageId = sessionState.Language.Id
        )

        accountGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            logonController = logonController,
            accountController = accountController,
            basketController = basketController,
            addressCascadeController = addressCascadeController
        )

        profileGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            profileController = profileController,
            addressCascadeController = addressCascadeController
        )

        settingsGraph(
            navigator = appNavigator,
            sessionState = sessionState,
            userSessionManager = userSessionManager,
            accountController = accountController,
            settingsController = settingsController
        )
    }

    if (showBuyerModeSheet) {
        BuyerModeSheet(
            currentMode = currentBuyerMode,
            onDismissRequest = {
                showBuyerModeSheet = false
            },
            onRetailClick = {
                appNavigator.navigateToRetailHome()
            },
            onWholesaleClick = {
                appNavigator.navigateToWholesaleHome()
            },
            onRfqClick = {
                appNavigator.navigateToWholesaleRfqCreate()
            }
        )
    }
}
