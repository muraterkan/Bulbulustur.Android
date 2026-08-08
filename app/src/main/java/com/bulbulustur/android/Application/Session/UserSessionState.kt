package com.bulbulustur.android.Application.Session

import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode
import com.bulbulustur.android.businesslayer.Core.Model.ApplicationLanguage

data class UserSessionState(
    val IsInitialized: Boolean = false,
    val ThemeMode: EThemeMode = EThemeMode.System,
    val Language: ApplicationLanguage = ApplicationLanguage(),
    val CountryId: Int = 0,
    val CountryName: String = BBLocalization.Current.Get(key = "5365b492-6a1c-4b46-b5c0-b50cbfdd17a8", fallback = "Türkiye"),
    val CountryCode: String = "TR",
    val CurrencyId: Int = 0,
    val CurrencyCode: String = "TRY",
    val CurrencyName: String = BBLocalization.Current.Get(key = "03f77d05-c54c-44f2-99f9-3dc82473eb51", fallback = "Türk Lirası"),
    val CurrencySymbol: String = "₺",
    val AuthenticationState: EAuthenticationState = EAuthenticationState.Initializing,
    val MemberId: Int = 0,
    val MemberName: String = "",
    val MemberSurname: String = "",
    val MemberFullName: String = "",
    val MemberProfession: String = "",
    val MemberPicture: String = ""
) {

    val IsAuthenticated: Boolean
        get() =
            AuthenticationState == EAuthenticationState.Authenticated &&
                    MemberId > 0

    val IsAnonymous: Boolean
        get() =
            AuthenticationState == EAuthenticationState.Anonymous

    val IsAuthenticationInitializing: Boolean
        get() =
            AuthenticationState == EAuthenticationState.Initializing
}
