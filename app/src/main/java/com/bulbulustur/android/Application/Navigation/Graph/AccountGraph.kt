package com.bulbulustur.android.Application.Navigation.Graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bulbulustur.android.Application.Controllers.AccountController
import com.bulbulustur.android.Application.Controllers.LogonController
import com.bulbulustur.android.Application.Navigation.BulbulusturNavigator
import com.bulbulustur.android.Application.Navigation.Routes.AccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.BankAccountRoutes
import com.bulbulustur.android.Application.Navigation.Routes.LogonRoutes
import com.bulbulustur.android.Application.Navigation.Routes.OrderRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RetailRoutes
import com.bulbulustur.android.Application.Navigation.Routes.RfqRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SettingsRoutes
import com.bulbulustur.android.Application.Navigation.Routes.SplashRoutes
import com.bulbulustur.android.Application.Navigation.Routes.StoreRoutes
import com.bulbulustur.android.Application.Navigation.Routes.WholesaleRoutes
import com.bulbulustur.android.Application.Session.UserSessionState
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeController
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeEvent
import com.bulbulustur.android.Application.Shared.Address.AddressCascadeSelection
import com.bulbulustur.android.Application.Views.Account.AccountScreen
import com.bulbulustur.android.Application.Views.Account.AccountSecurityScreen
import com.bulbulustur.android.Application.Views.Account.AddressCreateScreen
import com.bulbulustur.android.Application.Views.Account.AddressEditScreen
import com.bulbulustur.android.Application.Views.Account.AddressListScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountCreateScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountEditScreen
import com.bulbulustur.android.Application.Views.Account.BankAccountListScreen
import com.bulbulustur.android.Application.Views.Account.ChangeEmailScreen
import com.bulbulustur.android.Application.Views.Account.ChangePasswordScreen
import com.bulbulustur.android.Application.Views.Account.CompanyB2BIndexScreen
import com.bulbulustur.android.Application.Views.Account.CompanyB2BStatusScreen
import com.bulbulustur.android.Application.Views.Account.CompanyInfoEditScreen
import com.bulbulustur.android.Application.Views.Account.CompanyInfoScreen
import com.bulbulustur.android.Application.Views.Account.CouponListScreen
import com.bulbulustur.android.Application.Views.Account.FavoriteListScreen
import com.bulbulustur.android.Application.Views.Account.FollowedStoreListScreen
import com.bulbulustur.android.Application.Views.Account.LoginActivitiesScreen
import com.bulbulustur.android.Application.Views.Account.NotificationListScreen
import com.bulbulustur.android.Application.Views.Account.PhoneCreateScreen
import com.bulbulustur.android.Application.Views.Account.PhoneListScreen
import com.bulbulustur.android.Application.Views.Account.PhoneVerifyScreen
import com.bulbulustur.android.Application.Views.Account.ProfileEditScreen
import com.bulbulustur.android.Application.Views.Account.ProfileScreen
import com.bulbulustur.android.Application.Views.Account.RequestDetailScreen
import com.bulbulustur.android.Application.Views.Account.RequestListScreen
import com.bulbulustur.android.Application.Views.Account.ReviewEditScreen
import com.bulbulustur.android.Application.Views.Account.ReviewListScreen
import com.bulbulustur.android.Application.Views.Account.SubscriptionDetailScreen
import com.bulbulustur.android.Application.Views.Account.SubscriptionListScreen
import com.bulbulustur.android.Application.Views.Account.WalletBalanceScreen
import com.bulbulustur.android.Application.Views.Preference.UsagePurposeScreen
import com.bulbulustur.android.Application.Views.Question.QuestionAnswerScreen
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
import com.bulbulustur.android.businesslayer.Core.Model.ChangeMailModel
import com.bulbulustur.android.businesslayer.Core.Model.ChangePasswordModel
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberBankAccountInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberBankAccountUpdateModel

fun NavGraphBuilder.accountGraph(
    navigator: BulbulusturNavigator,
    sessionState: UserSessionState,
    logonController: LogonController,
    accountController: AccountController,
    addressCascadeController: AddressCascadeController
) {
    composable(route = AccountRoutes.AccountHome) {
        val logonState by logonController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        if (!sessionState.IsAuthenticated) {
            LaunchedEffect(Unit) {
                navigator.navController.navigate(LogonRoutes.Logon) {
                    popUpTo(AccountRoutes.AccountHome) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }

            return@composable
        }

        AccountScreen(
            isLogoutLoading = logonState.IsLoggingOut,
            onSecurityClick = {
                navigator.navController.navigate(AccountRoutes.Security)
            },
            onProfileClick = {
                navigator.navController.navigate(AccountRoutes.ProfileInfo)
            },
            onAddressClick = {
                navigator.navController.navigate(AccountRoutes.AddressList)
            },
            onNotificationClick = {
                navigator.navController.navigate(AccountRoutes.Notifications)
            },
            onCompanyInfoClick = {
                navigator.navController.navigate(AccountRoutes.CompanyInfo)
            },
            onFollowedStoresClick = {
                navigator.navController.navigate(AccountRoutes.FollowedStores)
            },
            onQuotationRequestsClick = {
                navigator.navigateToWholesaleOffers()
            },
            onOrdersClick = {
                navigator.navController.navigate(OrderRoutes.List)
            },
            onFavoritesClick = {
                navigator.navigateToFavorites()
            },
            onReviewsClick = {
                navigator.navController.navigate(AccountRoutes.Reviews)
            },
            onCouponsClick = {
                navigator.navController.navigate(AccountRoutes.Coupons)
            },
            onRequestsClick = {
                navigator.navController.navigate(AccountRoutes.Requests)
            },
            onSubscriptionsClick = {
                navigator.navController.navigate(AccountRoutes.Subscriptions)
            },
            onWalletBalanceClick = {
                navigator.navController.navigate(AccountRoutes.WalletBalance)
            },
            onBankAccountsClick = {
                navigator.navController.navigate(BankAccountRoutes.List)
            },
            onSettingsClick = {
                navigator.navController.navigate(SettingsRoutes.Home)
            },
            onMessagesClick = {
                navigator.navigateToInbox()
            },
            onSupportClick = {
            },
            onLogoutClick = {
                logonController.LogoutPost(
                    languageId = languageId,
                    onCompleted = {
                        navigator.navController.navigate(SplashRoutes.ModeSelection) {
                            popUpTo(navigator.navController.graph.startDestinationId) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            },
            onQuestionsClick = {
                navigator.navController.navigate(AccountRoutes.QuestionAnswers)
            },
            onUsagePurposeClick = {
                navigator.navController.navigate(AccountRoutes.UsagePurpose)
            },
            onHomeClick = {
                navigator.navigateToRetailHome()
            },
            onMenuClick = {
                navigator.navigateToRetailCategories()
            },
            onModeSwitchClick = {
                navigator.openModeSheet()
            },
            onBasketClick = {
                navigator.navigateToRetailBasket()
            }
        )
    }

    composable(route = AccountRoutes.Security) {
        AccountSecurityScreen(
            onBackClick = {
                navigator.back()
            },
            onProfileInfoClick = {
                navigator.navController.navigate(AccountRoutes.ProfileInfo)
            },
            onEmailChangeClick = {
                navigator.navController.navigate(AccountRoutes.EmailChange)
            },
            onPasswordChangeClick = {
                navigator.navController.navigate(AccountRoutes.PasswordChange)
            },
            onPhonesClick = {
                navigator.navController.navigate(AccountRoutes.PhoneList)
            },
            onLoginActivitiesClick = {
                navigator.navController.navigate(AccountRoutes.LoginActivities)
            }
        )
    }

    composable(route = AccountRoutes.EmailChange) {
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        val currentEmail = accountState.Member?.Email.orEmpty()

        LaunchedEffect(sessionState.MemberId) {
            accountController.ResetChangeMailState()

            if (accountState.Member == null) {
                accountController.GetAccount(
                    languageId = languageId,
                    memberId = sessionState.MemberId
                )
            }
        }

        ChangeEmailScreen(
            currentEmail = currentEmail,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetAccount" ||
                            accountState.CurrentAction == "SendEmailChangingRequest"
                    ),
            errorMessage = accountState.ChangeMailResult
                ?.takeIf { !it.Success }
                ?.Message,
            successMessage = accountState.ChangeMailMessage,
            onBackClick = {
                navigator.back()
            },
            onSaveClick = { newEmail, reNewEmail ->
                accountController.SendEmailChangingRequest(
                    model = ChangeMailModel(
                        MemberId = sessionState.MemberId,
                        Email = currentEmail,
                        NewEmail = newEmail,
                        ReNewEmail = reNewEmail,
                        LanguageId = languageId
                    )
                )
            }
        )
    }

    composable(route = AccountRoutes.PasswordChange) {
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(Unit) {
            accountController.ResetPasswordChangeState()
        }

        ChangePasswordScreen(
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "ChangePassword",
            errorMessage = accountState.PasswordChangeResult
                ?.takeIf { !it.Success }
                ?.Message,
            successMessage = accountState.PasswordChangeMessage,
            onBackClick = {
                navigator.back()
            },
            onSaveClick = { oldPassword, newPassword, newPasswordAgain ->
                accountController.ChangePassword(
                    languageId = languageId,
                    model = ChangePasswordModel(
                        MemberId = sessionState.MemberId,
                        ActivePassword = oldPassword,
                        NewPassword = newPassword,
                        ReNewPassword = newPasswordAgain,
                        LanguageId = languageId
                    )
                )
            }
        )
    }

    composable(route = AccountRoutes.LoginActivities) {
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetLoginActivities(memberId = sessionState.MemberId)
        }

        LoginActivitiesScreen(
            activities = accountState.LoginActivities,
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "GetLoginActivities",
            errorMessage = accountState.LoginActivityListResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                accountController.GetLoginActivities(memberId = sessionState.MemberId)
            }
        )
    }

    composable(route = AccountRoutes.ProfileInfo) {
        val accountState by accountController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        val member = accountState.Member

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetAccount(
                languageId = languageId,
                memberId = sessionState.MemberId
            )
        }

        LaunchedEffect(
            member?.MemberId,
            member?.CountryId,
            member?.CountryStateId,
            member?.CountryDepartmentId,
            member?.CityId,
            member?.DistrictId
        ) {
            if (member == null) return@LaunchedEffect

            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId = member.CountryId,
                        CountryStateId = member.CountryStateId,
                        CountryDepartmentId = member.CountryDepartmentId,
                        CityId = member.CityId,
                        DistrictId = member.DistrictId
                    ),
                    LanguageId = languageId
                )
            )
        }

        ProfileScreen(
            member = member,
            addressCascadeState = addressCascadeState,
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "GetAccount",
            errorMessage = accountState.MemberResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onEditClick = {
                navigator.navController.navigate(AccountRoutes.ProfileEdit)
            },
            onPhonesClick = {
                navigator.navController.navigate(AccountRoutes.PhoneList)
            },
            onEmailClick = {
                navigator.navController.navigate(AccountRoutes.EmailChange)
            },
            onCompanyInfoClick = {
                navigator.navController.navigate(AccountRoutes.CompanyInfo)
            },
            onB2BStatusClick = {
                navigator.navController.navigate(AccountRoutes.CompanyB2BStatus)
            }
        )
    }

    composable(route = AccountRoutes.ProfileEdit) {
        val accountState by accountController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        val member = accountState.MemberUpdateResult?.Data

        LaunchedEffect(sessionState.MemberId) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)

            accountController.GetMember(
                languageId = languageId,
                memberId = sessionState.MemberId
            )
        }

        LaunchedEffect(
            member?.MemberId,
            member?.CountryId,
            member?.CountryStateId,
            member?.CountryDepartmentId,
            member?.CityId,
            member?.DistrictId
        ) {
            if (member == null) return@LaunchedEffect

            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId = member.CountryId,
                        CountryStateId = member.CountryStateId,
                        CountryDepartmentId = member.CountryDepartmentId,
                        CityId = member.CityId,
                        DistrictId = member.DistrictId
                    ),
                    LanguageId = languageId
                )
            )
        }

        ProfileEditScreen(
            member = member,
            addressCascadeState = addressCascadeState,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetMember" ||
                            accountState.CurrentAction == "UpdateMember"
                    ),
            errorMessage = accountState.ErrorMessage,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onCountrySelected = { countryId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountry(
                        CountryId = countryId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryStateSelected = { countryStateId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryState(
                        CountryStateId = countryStateId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryDepartmentSelected = { countryDepartmentId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryDepartment(
                        CountryDepartmentId = countryDepartmentId,
                        LanguageId = languageId
                    )
                )
            },
            onCitySelected = { cityId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCity(
                        CityId = cityId,
                        LanguageId = languageId
                    )
                )
            },
            onDistrictSelected = { districtId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectDistrict(DistrictId = districtId)
                )
            },
            onSaveClick = { name, surname, profession, birthDate ->
                val currentMember = member ?: return@ProfileEditScreen
                val selection = addressCascadeState.Selection

                accountController.UpdateMember(
                    model = currentMember.copy(
                        Name = name.trim(),
                        Surname = surname.trim(),
                        Profession = profession.trim(),
                        BirthDate = birthDate.trim().ifBlank { null },
                        CountryId = selection.CountryId,
                        CountryStateId = selection.CountryStateId,
                        CountryDepartmentId = selection.CountryDepartmentId,
                        CityId = selection.CityId,
                        DistrictId = selection.DistrictId
                    ),
                    onSuccess = {
                        addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                        navigator.back()
                    }
                )
            }
        )
    }

    composable(route = AccountRoutes.PhoneList) {
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetPhones(
                languageId = languageId,
                memberId = sessionState.MemberId
            )
        }

        PhoneListScreen(
            phones = accountState.Phones,
            isLoading = accountState.IsLoading,
            currentAction = accountState.CurrentAction,
            errorMessage = accountState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onCreatePhoneClick = {
                navigator.navController.navigate(AccountRoutes.PhoneCreate)
            },
            onVerifyPhoneClick = { memberPhoneId ->
                accountController.SendPhoneVerificationSms(
                    languageId = languageId,
                    memberId = sessionState.MemberId,
                    memberPhoneId = memberPhoneId,
                    onSuccess = {
                        navigator.navController.navigate(
                            AccountRoutes.PhoneVerify(memberPhoneId)
                        )
                    }
                )
            },
            onDeletePhoneClick = { memberPhoneId ->
                accountController.DeletePhone(
                    languageId = languageId,
                    memberId = sessionState.MemberId,
                    memberPhoneId = memberPhoneId,
                    onSuccess = {
                        accountController.GetPhones(
                            languageId = languageId,
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onRetryClick = {
                accountController.GetPhones(
                    languageId = languageId,
                    memberId = sessionState.MemberId
                )
            }
        )
    }

    composable(route = AccountRoutes.PhoneCreate) {
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(Unit) {
            accountController.ResetPhoneState()
        }

        PhoneCreateScreen(
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "InsertPhone",
            errorMessage = accountState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onSaveClick = { phone ->
                accountController.InsertPhone(
                    languageId = languageId,
                    memberId = sessionState.MemberId,
                    phone = phone,
                    onSuccess = { memberPhoneId ->
                        accountController.SendPhoneVerificationSms(
                            languageId = languageId,
                            memberId = sessionState.MemberId,
                            memberPhoneId = memberPhoneId,
                            onSuccess = {
                                navigator.navController.navigate(
                                    AccountRoutes.PhoneVerify(memberPhoneId)
                                ) {
                                    popUpTo(AccountRoutes.PhoneCreate) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                )
            }
        )
    }

    composable(
        route = AccountRoutes.PhoneVerify,
        arguments = listOf(
            navArgument("memberPhoneId") {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val accountState by accountController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        val memberPhoneId = backStackEntry.arguments
            ?.getInt("memberPhoneId")
            ?: 0

        val phone = accountState.PhoneDetailResult
            ?.Data
            ?.Phone
            .orEmpty()

        LaunchedEffect(memberPhoneId, sessionState.MemberId) {
            accountController.ResetPhoneState()

            accountController.GetPhone(
                languageId = languageId,
                memberPhoneId = memberPhoneId,
                memberId = sessionState.MemberId
            )
        }

        PhoneVerifyScreen(
            phone = phone,
            isLoading = accountState.IsLoading,
            currentAction = accountState.CurrentAction,
            errorMessage = accountState.ErrorMessage,
            successMessage = accountState.PhoneMessage,
            onBackClick = {
                navigator.back()
            },
            onVerifyClick = { verificationCode ->
                accountController.VerifyPhone(
                    languageId = languageId,
                    memberId = sessionState.MemberId,
                    memberPhoneId = memberPhoneId,
                    verificationCode = verificationCode,
                    onSuccess = {
                        navigator.navController.navigate(AccountRoutes.PhoneList) {
                            popUpTo(AccountRoutes.PhoneList) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            },
            onResendCodeClick = {
                accountController.SendPhoneVerificationSms(
                    languageId = languageId,
                    memberId = sessionState.MemberId,
                    memberPhoneId = memberPhoneId
                )
            }
        )
    }

    composable(route = AccountRoutes.AddressList) {
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetAddresses(memberId = sessionState.MemberId)
        }

        AddressListScreen(
            addresses = accountState.Addresses,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetAddresses" ||
                            accountState.CurrentAction == "DeleteAddress"
                    ),
            errorMessage = accountState.AddressListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?: accountState.AddressDeleteResult
                    ?.takeIf { !it.Success }
                    ?.Message,
            deletingAddressId = null,
            onBackClick = {
                navigator.back()
            },
            onCreateAddressClick = {
                navigator.navController.navigate(AccountRoutes.AddressCreate)
            },
            onEditAddressClick = { addressKey ->
                navigator.navController.navigate(
                    AccountRoutes.editAddress(addressKey)
                )
            },
            onDeleteAddressClick = { addressId ->
                accountController.DeleteAddress(
                    memberId = sessionState.MemberId,
                    addressId = addressId,
                    onSuccess = {
                        accountController.GetAddresses(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onRetryClick = {
                accountController.GetAddresses(memberId = sessionState.MemberId)
            }
        )
    }

    composable(route = AccountRoutes.AddressCreate) {
        val accountState by accountController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        LaunchedEffect(Unit) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)

            addressCascadeController.OnEvent(
                AddressCascadeEvent.LoadCountries(LanguageId = languageId)
            )
        }

        AddressCreateScreen(
            addressCascadeState = addressCascadeState,
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "InsertAddress",
            errorMessage = accountState.AddressInsertResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onCountrySelected = { countryId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountry(
                        CountryId = countryId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryStateSelected = { countryStateId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryState(
                        CountryStateId = countryStateId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryDepartmentSelected = { countryDepartmentId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryDepartment(
                        CountryDepartmentId = countryDepartmentId,
                        LanguageId = languageId
                    )
                )
            },
            onCitySelected = { cityId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCity(
                        CityId = cityId,
                        LanguageId = languageId
                    )
                )
            },
            onDistrictSelected = { districtId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectDistrict(DistrictId = districtId)
                )
            },
            onSaveClick = { model ->
                accountController.InsertAddress(
                    memberId = sessionState.MemberId,
                    model = model,
                    onSuccess = {
                        addressCascadeController.OnEvent(AddressCascadeEvent.Clear)

                        navigator.navController.navigate(AccountRoutes.AddressList) {
                            popUpTo(AccountRoutes.AddressList) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        )
    }

    composable(
        route = AccountRoutes.AddressEdit,
        arguments = listOf(
            navArgument("addressKey") {
                type = NavType.StringType
            }
        )
    ) { backStackEntry ->
        val accountState by accountController.State.collectAsState()
        val addressCascadeState by addressCascadeController.State.collectAsState()

        val languageId = when (sessionState.Language) {
            EApplicationLanguage.Turkish -> 1
            EApplicationLanguage.English -> 2
        }

        val addressKey = backStackEntry.arguments
            ?.getString("addressKey")
            .orEmpty()

        val address = accountState.Address?.takeIf {
            it.AddressKey == addressKey
        }

        LaunchedEffect(addressKey, sessionState.MemberId) {
            addressCascadeController.OnEvent(AddressCascadeEvent.Clear)

            accountController.GetAddress(
                memberId = sessionState.MemberId,
                addressKey = addressKey
            )
        }

        LaunchedEffect(
            address?.AddressKey,
            address?.CountryId,
            address?.CountryStateId,
            address?.CountryDepartmentId,
            address?.CityId,
            address?.DistrictId
        ) {
            val currentAddress = address ?: return@LaunchedEffect

            addressCascadeController.OnEvent(
                AddressCascadeEvent.SetInitialSelection(
                    Selection = AddressCascadeSelection(
                        CountryId = currentAddress.CountryId ?: 0,
                        CountryStateId = currentAddress.CountryStateId ?: 0,
                        CountryDepartmentId = currentAddress.CountryDepartmentId,
                        CityId = currentAddress.CityId ?: 0,
                        DistrictId = currentAddress.DistrictId
                    ),
                    LanguageId = languageId
                )
            )
        }

        AddressEditScreen(
            address = address,
            addressCascadeState = addressCascadeState,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetAddress" ||
                            accountState.CurrentAction == "UpdateAddress"
                    ),
            errorMessage = accountState.AddressDetailResult
                ?.takeIf { !it.Success }
                ?.Message
                ?: accountState.AddressUpdateResult
                    ?.takeIf { !it.Success }
                    ?.Message,
            onBackClick = {
                addressCascadeController.OnEvent(AddressCascadeEvent.Clear)
                navigator.back()
            },
            onCountrySelected = { countryId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountry(
                        CountryId = countryId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryStateSelected = { countryStateId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryState(
                        CountryStateId = countryStateId,
                        LanguageId = languageId
                    )
                )
            },
            onCountryDepartmentSelected = { countryDepartmentId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCountryDepartment(
                        CountryDepartmentId = countryDepartmentId,
                        LanguageId = languageId
                    )
                )
            },
            onCitySelected = { cityId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectCity(
                        CityId = cityId,
                        LanguageId = languageId
                    )
                )
            },
            onDistrictSelected = { districtId ->
                addressCascadeController.OnEvent(
                    AddressCascadeEvent.SelectDistrict(DistrictId = districtId)
                )
            },
            onSaveClick = { model ->
                accountController.UpdateAddress(
                    memberId = sessionState.MemberId,
                    model = model,
                    onSuccess = {
                        addressCascadeController.OnEvent(AddressCascadeEvent.Clear)

                        navigator.navController.navigate(AccountRoutes.AddressList) {
                            popUpTo(AccountRoutes.AddressList) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    }
                )
            }
        )
    }

    composable(route = AccountRoutes.Notifications) {
        NotificationListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.CompanyInfo) {
        CompanyInfoScreen(
            onBackClick = {
                navigator.back()
            },
            onEditClick = {
                navigator.navController.navigate(AccountRoutes.CompanyInfoEdit)
            },
            onB2BIndexClick = {
                navigator.navController.navigate(AccountRoutes.CompanyB2BIndex)
            },
            onB2CStoreClick = {
            }
        )
    }

    composable(route = AccountRoutes.CompanyInfoEdit) {
        CompanyInfoEditScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.CompanyB2BIndex) {
        CompanyB2BIndexScreen(
            onBackClick = {
                navigator.back()
            },
            onActivateClick = {
                navigator.navController.navigate(AccountRoutes.CompanyB2BStatus)
            }
        )
    }

    composable(route = AccountRoutes.CompanyB2BStatus) {
        CompanyB2BStatusScreen(
            onBackClick = {
                navigator.back()
            },
            onB2BManagementPanelClick = {
            }
        )
    }

    composable(route = AccountRoutes.UsagePurpose) {
        UsagePurposeScreen(
            onBackClick = {
                navigator.back()
            },
            onRetailClick = {
                navigator.back()
            },
            onWholesaleClick = {
                navigator.back()
            },
            onBothClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.QuestionAnswers) {
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetMemberProductCustomerQuestions(memberId = sessionState.MemberId)
        }

        QuestionAnswerScreen(
            questions = accountState.ProductCustomerQuestions,
            isLoading = accountState.IsLoading && accountState.CurrentAction == "GetMemberProductCustomerQuestions",
            errorMessage = accountState.ProductCustomerQuestionListResult?.takeIf { !it.Success }?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                accountController.GetMemberProductCustomerQuestions(memberId = sessionState.MemberId)
            },
            onProductClick = { productId, storeId, variantId ->
                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = productId,
                        storeId = storeId,
                        variantId = variantId
                    )
                )
            }
        )
    }

    composable(
        route = AccountRoutes.FollowedStores
    ) {
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetFollowedStores(
                memberId = sessionState.MemberId
            )
        }

        FollowedStoreListScreen(
            stores = accountState.FollowedStores,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetFollowedStores" ||
                            accountState.CurrentAction == "DeleteFollowedStore"
                    ),
            errorMessage = accountState.FollowedStoreListResult
                ?.takeIf { !it.Success }
                ?.Message
                ?: accountState.FollowedStoreDeleteResult
                    ?.takeIf { !it.Success }
                    ?.Message,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                accountController.GetFollowedStores(
                    memberId = sessionState.MemberId
                )
            },
            onStoreClick = { storeId: Int ->
                navigator.navController.navigate(
                    StoreRoutes.storeDetail(storeId)
                )
            },
            onUnfollowStoreClick = { followedStoreId: Int ->
                accountController.DeleteFollowedStore(
                    memberId = sessionState.MemberId,
                    followedStoreId = followedStoreId,
                    onSuccess = {
                        accountController.GetFollowedStores(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            }
        )
    }

    composable(route = AccountRoutes.Favorites) {
        val accountState by accountController.State.collectAsState()

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetFavorites(memberId = sessionState.MemberId)
        }

        FavoriteListScreen(
            retailFavorites = accountState.ProductFavorites,
            wholesaleFavorites = accountState.WholesaleFavorites,
            isLoading = accountState.IsLoading && (
                    accountState.CurrentAction == "GetFavorites" ||
                            accountState.CurrentAction == "GetProductFavorites" ||
                            accountState.CurrentAction == "GetWholesaleFavorites" ||
                            accountState.CurrentAction == "MoveProductFavoriteToBasket"
                    ),
            errorMessage = accountState.ErrorMessage,
            onBackClick = {
                navigator.back()
            },
            onRetryClick = {
                accountController.GetFavorites(memberId = sessionState.MemberId)
            },
            onRetailProductClick = { productId, storeId, variantId ->
                navigator.navController.navigate(
                    RetailRoutes.productDetail(
                        productId = productId,
                        storeId = storeId,
                        variantId = variantId
                    )
                )
            },
            onWholesaleProductClick = { wholesaleProductId ->
                navigator.navController.navigate(
                    WholesaleRoutes.productDetail(wholesaleProductId)
                )
            },
            onRemoveRetailFavoriteClick = { favoriteId ->
                accountController.DeleteProductFavorite(
                    memberId = sessionState.MemberId,
                    favoriteId = favoriteId,
                    onSuccess = {
                        accountController.GetProductFavorites(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onRemoveWholesaleFavoriteClick = { wholesaleFavoriteId ->
                accountController.DeleteWholesaleFavorite(
                    memberId = sessionState.MemberId,
                    wholesaleFavoriteId = wholesaleFavoriteId,
                    onSuccess = {
                        accountController.GetWholesaleFavorites(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onAddRetailFavoriteToBasketClick = { favoriteId ->
                accountController.MoveProductFavoriteToBasket(
                    memberId = sessionState.MemberId,
                    favoriteId = favoriteId,
                    onSuccess = {
                        accountController.GetProductFavorites(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onRequestQuoteClick = {
                navigator.navController.navigate(RfqRoutes.Create)
            }
        )
    }

    composable(route = AccountRoutes.Reviews) {
        ReviewListScreen(
            onBackClick = {
                navigator.back()
            },
            onProductClick = {
                navigator.navController.navigate(RetailRoutes.ProductList)
            },
            onEditReviewClick = {
                navigator.navController.navigate(AccountRoutes.ReviewEdit)
            },
            onDeleteReviewClick = {
            }
        )
    }

    composable(route = AccountRoutes.ReviewEdit) {
        ReviewEditScreen(
            onBackClick = {
                navigator.back()
            },
            onSaveClick = {
                navigator.back()
            },
            onDeleteClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.Coupons) {
        CouponListScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.Requests) {
        RequestListScreen(
            onBackClick = {
                navigator.back()
            },
            onRequestDetailClick = { requestId ->
                navigator.navController.navigate(
                    AccountRoutes.requestDetail(requestId)
                )
            },
            onOrderListClick = {
                navigator.navController.navigate(OrderRoutes.List)
            }
        )
    }

    composable(
        route = AccountRoutes.RequestDetail,
        arguments = listOf(
            navArgument("requestId") {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val requestId = backStackEntry.arguments
            ?.getInt("requestId")
            ?: 0

        RequestDetailScreen(
            onBackClick = {
                navigator.back()
            },
            onOrderClick = {
                navigator.navController.navigate(
                    AccountRoutes.OrderDetail
                )
            },
            onStoreClick = {
                navigator.navController.navigate(
                    StoreRoutes.StoreList
                )
            }
        )
    }

    composable(route = AccountRoutes.Subscriptions) {
        SubscriptionListScreen(
            onBackClick = {
                navigator.back()
            },
            onSubscriptionDetailClick = {
                navigator.navController.navigate(
                    AccountRoutes.SubscriptionDetail
                )
            }
        )
    }

    composable(route = AccountRoutes.SubscriptionDetail) {
        SubscriptionDetailScreen(
            onBackClick = {
                navigator.back()
            }
        )
    }

    composable(route = AccountRoutes.WalletBalance) {
        WalletBalanceScreen(
            onBackClick = {
                navigator.back()
            },
            onBankAccountsClick = {
                navigator.navController.navigate(BankAccountRoutes.List)
            }
        )
    }

    composable(route = BankAccountRoutes.List) {
        val accountState by accountController.State.collectAsState()
        val clipboardManager = LocalClipboardManager.current

        LaunchedEffect(sessionState.MemberId) {
            accountController.GetBankAccounts(memberId = sessionState.MemberId)
        }

        BankAccountListScreen(
            bankAccounts = accountState.BankAccounts,
            isLoading = accountState.IsLoading,
            currentAction = accountState.CurrentAction,
            errorMessage = accountState.BankAccountListResult
                ?.takeIf { !it.Success }
                ?.Message,
            onBackClick = {
                navigator.back()
            },
            onCreateBankAccountClick = {
                navigator.navController.navigate(BankAccountRoutes.Create)
            },
            onEditBankAccountClick = { bankAccountId ->
                navigator.navController.navigate(
                    BankAccountRoutes.edit(bankAccountId)
                )
            },
            onDeleteBankAccountClick = { bankAccountId ->
                accountController.DeleteBankAccount(
                    memberId = sessionState.MemberId,
                    bankAccountId = bankAccountId,
                    onSuccess = {
                        accountController.GetBankAccounts(
                            memberId = sessionState.MemberId
                        )
                    }
                )
            },
            onCopyIbanClick = { iban ->
                clipboardManager.setText(AnnotatedString(iban))
            },
            onRetryClick = {
                accountController.GetBankAccounts(memberId = sessionState.MemberId)
            }
        )
    }

    composable(route = BankAccountRoutes.Create) {
        val accountState by accountController.State.collectAsState()

        BankAccountCreateScreen(
            isSubmitting = accountState.IsLoading &&
                    accountState.CurrentAction == "InsertBankAccount",
            onBackClick = {
                navigator.back()
            },
            onBankAccountCreateClick = { formState ->
                accountController.InsertBankAccount(
                    memberId = sessionState.MemberId,
                    model = MemberBankAccountInsertModel(
                        InsertedBy = sessionState.MemberId,
                        MemberId = sessionState.MemberId,
                        BankIban = formState.normalizedIban
                    ),
                    onSuccess = {
                        accountController.GetBankAccounts(
                            memberId = sessionState.MemberId
                        )

                        navigator.back()
                    }
                )
            }
        )
    }

    composable(
        route = BankAccountRoutes.Edit,
        arguments = listOf(
            navArgument("bankAccountId") {
                type = NavType.IntType
            }
        )
    ) { backStackEntry ->
        val accountState by accountController.State.collectAsState()

        val bankAccountId = backStackEntry.arguments
            ?.getInt("bankAccountId")
            ?: 0

        val bankAccountDetailResult = accountState.BankAccountDetailResult
        val bankAccountUpdateResult = accountState.BankAccountUpdateResult

        val currentBankAccount = accountState.BankAccount?.takeIf {
            it.MemberBankAccountId == bankAccountId
        }

        val errorMessage = when {
            bankAccountDetailResult?.Success == false ->
                bankAccountDetailResult.Message

            bankAccountUpdateResult?.Success == false ->
                bankAccountUpdateResult.Message

            else -> null
        }

        LaunchedEffect(sessionState.MemberId, bankAccountId) {
            accountController.GetBankAccount(
                memberId = sessionState.MemberId,
                bankAccountId = bankAccountId
            )
        }

        BankAccountEditScreen(
            bankAccountId = bankAccountId,
            initialIban = currentBankAccount?.BankIban.orEmpty(),
            isLoading = accountState.IsLoading &&
                    accountState.CurrentAction == "GetBankAccount",
            isSubmitting = accountState.IsLoading &&
                    accountState.CurrentAction == "UpdateBankAccount",
            errorMessage = errorMessage,
            onBackClick = {
                navigator.back()
            },
            onSaveClick = { iban ->
                val bankAccount =
                    currentBankAccount ?: return@BankAccountEditScreen

                accountController.UpdateBankAccount(
                    memberId = sessionState.MemberId,
                    model = MemberBankAccountUpdateModel(
                        MemberBankAccountId = bankAccount.MemberBankAccountId,
                        InsertedBy = bankAccount.InsertedBy,
                        InsertedDate = bankAccount.InsertedDate,
                        StatusId = bankAccount.StatusId,
                        BankId = bankAccount.BankId,
                        BankIban = iban,
                        MemberId = bankAccount.MemberId
                    ),
                    onSuccess = {
                        accountController.GetBankAccounts(
                            memberId = sessionState.MemberId
                        )

                        navigator.back()
                    }
                )
            }
        )
    }
}