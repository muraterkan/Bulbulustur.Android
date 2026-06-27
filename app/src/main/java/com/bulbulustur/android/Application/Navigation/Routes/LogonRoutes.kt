package com.bulbulustur.android.Application.Navigation.Routes

import android.net.Uri

object LogonRoutes {

    const val Logon =
        "logon"

    const val ForgotPassword =
        "logon/forgot-password"

    const val RegisterStart =
        "logon/register"

    const val RegisterActivationArgument =
        "activationCode"

    const val RegisterActivation =
        "logon/register/activation/{$RegisterActivationArgument}"

    fun CreateRegisterActivationRoute(
        activationCode: String
    ): String {
        return "logon/register/activation/${
            Uri.encode(
                activationCode
            )
        }"
    }

    const val FirstDoor =
        "logon/first-door"

    const val RegisterFinal =
        "logon/final"

    const val Expired =
        "logon/expired"
}