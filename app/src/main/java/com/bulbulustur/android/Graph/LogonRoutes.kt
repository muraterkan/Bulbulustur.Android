package com.bulbulustur.android.Graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

object LogonRoutes {

    const val Logon = "logon"
    const val ForgotPassword = "logon/forgot-password"
    const val RegisterStart = "logon/register"
    const val FirstDoor = "logon/first-door"
    const val RegisterFinal = "logon/final"
    const val Expired = "logon/expired"
}

fun NavGraphBuilder.logonGraph(
    navController: NavHostController
) {
    composable(
        route = LogonRoutes.Logon
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.LoginScreen(
            onLogonClick = { email, password ->
                /*
                 * API bağlandığında:
                 * - email/password Login endpoint'e gönderilecek
                 * - token alınırsa app ana akışına geçilecek
                 * - hata varsa ekranda gösterilecek
                 */
            },
            onForgotPasswordClick = {
                navController.navigate(LogonRoutes.ForgotPassword)
            },
            onRegisterClick = {
                navController.navigate(LogonRoutes.FirstDoor)
            },
            onGoogleClick = {
                /*
                 * ExternalLogin() mobil karşılığı burada başlatılacak.
                 */
            },
            onFacebookClick = {
                /*
                 * ExternalLogin() mobil karşılığı burada başlatılacak.
                 */
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }

    composable(
        route = LogonRoutes.ForgotPassword
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.ForgotPasswordScreen(
            onSendResetLinkClick = { email ->
                /*
                 * API bağlandığında:
                 * - forgotpassword endpoint'e email gönderilecek
                 * - başarılıysa bilgi mesajı gösterilecek
                 */
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }

    composable(
        route = LogonRoutes.FirstDoor
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.FirstDoorScreen(
            onContinueClick = { selectedDoor ->
                when (selectedDoor) {
                    _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.FirstDoorType.IndividualBuyer -> {
                        navController.navigate(LogonRoutes.RegisterStart)
                    }

                    _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.FirstDoorType.CompanyBuyer -> {
                        navController.navigate(LogonRoutes.RegisterStart)
                    }

                    _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.FirstDoorType.ExistingAccount -> {
                        navController.popBackStack(
                            route = LogonRoutes.Logon,
                            inclusive = false
                        )
                    }
                }
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }

    composable(
        route = LogonRoutes.RegisterStart
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.RegisterStartScreen(
            onContinueClick = { registerStartForm ->
                /*
                 * API bağlandığında:
                 * - registerStartForm kayıt endpoint'e gönderilecek
                 * - başarılıysa final ekranına gidilecek
                 */
                navController.navigate(LogonRoutes.RegisterFinal)
            },
            onBackToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }

    composable(
        route = LogonRoutes.RegisterFinal
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.RegisterFinalScreen(
            onGoToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onResendVerificationClick = {
                /*
                 * API bağlandığında:
                 * - doğrulama e-postası tekrar gönderilecek
                 */
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }

    composable(
        route = LogonRoutes.Expired
    ) {
        _root_ide_package_.com.bulbulustur.android.Application.Views.Logon.ExpiredScreen(
            onSendAgainClick = {
                /*
                 * expiredType'a göre:
                 * - doğrulama e-postası
                 * - şifre yenileme bağlantısı
                 * - kayıt akışı
                 * yeniden başlatılacak.
                 */
            },
            onGoToLogonClick = {
                navController.popBackStack(
                    route = LogonRoutes.Logon,
                    inclusive = false
                )
            },
            onLanguageClick = {
                /*
                 * Dil seçimi hazır route'a bağlanacak.
                 */
            }
        )
    }
}
