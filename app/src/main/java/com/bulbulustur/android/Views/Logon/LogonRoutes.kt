package com.bulbulustur.android.Views.Logon

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
        LoginScreen(
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
        ForgotPasswordScreen(
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
        FirstDoorScreen(
            onContinueClick = { selectedDoor ->
                when (selectedDoor) {
                    FirstDoorType.IndividualBuyer -> {
                        navController.navigate(LogonRoutes.RegisterStart)
                    }

                    FirstDoorType.CompanyBuyer -> {
                        navController.navigate(LogonRoutes.RegisterStart)
                    }

                    FirstDoorType.ExistingAccount -> {
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
        RegisterStartScreen(
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
        RegisterFinalScreen(
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
        ExpiredScreen(
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