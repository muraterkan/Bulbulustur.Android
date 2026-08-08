package com.bulbulustur.android.Application.Session

import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.businesslayer.Core.Enums.EThemeMode
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Security.JwtMemberIdParser
import com.bulbulustur.android.businesslayer.Core.Security.SecureTokenStore
import com.bulbulustur.android.businesslayer.Core.Security.TokenExpirationParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserSessionManager(
    private val userPreferenceDataStore: UserPreferenceDataStore,
    private val secureTokenStore: SecureTokenStore,
    private val coroutineScope: CoroutineScope
) {

    private val _state =
        MutableStateFlow(
            UserSessionState()
        )

    val State: StateFlow<UserSessionState> =
        _state.asStateFlow()

    init {
        ObservePreferences()
        RestoreAuthentication()
    }

    fun SetThemeMode(
        themeMode: EThemeMode
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetThemeMode(
                themeMode
            )
        }
    }

    fun SetLanguage(
        languageId: Int,
        languageCode: String
    ) {
        if (languageId <= 0) return

        coroutineScope.launch {
            userPreferenceDataStore.SetLanguage(
                languageId = languageId,
                languageCode = languageCode.trim()
            )
        }
    }

    fun SetCountry(
        countryId: Int,
        countryName: String,
        countryCode: String
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetCountry(
                countryId = countryId,
                countryName = countryName,
                countryCode = countryCode
            )
        }
    }

    fun SetCurrency(
        currencyId: Int,
        currencyCode: String,
        currencyName: String,
        currencySymbol: String
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetCurrency(
                currencyId = currencyId,
                currencyCode = currencyCode,
                currencyName = currencyName,
                currencySymbol = currencySymbol
            )
        }
    }

    fun RestoreAuthentication() {
        coroutineScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    AuthenticationState =
                        EAuthenticationState.Initializing,
                    MemberId =
                        0,
                    MemberName =
                        "",
                    MemberSurname =
                        "",
                    MemberFullName =
                        "",
                    MemberProfession =
                        "",
                    MemberPicture =
                        ""
                )
            }

            val storedTokens =
                secureTokenStore.ReadTokens()

            if (
                storedTokens == null ||
                !storedTokens.HasTokens
            ) {
                SetAnonymous()
                return@launch
            }

            val isValid =
                TokenExpirationParser.IsValid(
                    storedTokens.Expiration
                )

            if (!isValid) {
                secureTokenStore.Clear()
                SetAnonymous()
                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    AuthenticationState =
                        EAuthenticationState.Authenticated,
                    MemberId =
                        storedTokens.MemberId,
                    MemberName =
                        storedTokens.MemberName,
                    MemberSurname =
                        storedTokens.MemberSurname,
                    MemberFullName =
                        storedTokens.MemberFullName,
                    MemberProfession =
                        storedTokens.MemberProfession,
                    MemberPicture =
                        storedTokens.MemberPicture
                )
            }
        }
    }

    fun SetAuthenticated(
        authResponse: AuthResponse
    ): Boolean {
        val responseMemberId =
            authResponse.Member.MemberId

        val tokenMemberId =
            JwtMemberIdParser.Parse(
                authResponse.Token
            )

        val memberId =
            if (responseMemberId > 0) {
                responseMemberId
            } else {
                tokenMemberId
            }

        if (memberId <= 0) {
            SetAnonymous()
            return false
        }

        val memberFullName =
            authResponse.Member.FullName
                .ifBlank {
                    listOf(
                        authResponse.Member.Name,
                        authResponse.Member.Surname
                    )
                        .filter {
                            it.isNotBlank()
                        }
                        .joinToString(" ")
                }

        val saved =
            secureTokenStore.SaveTokens(
                accessToken = authResponse.Token,
                refreshToken = authResponse.RefreshToken,
                expiration = authResponse.Expiration,
                memberId = memberId,
                memberName = authResponse.Member.Name,
                memberSurname = authResponse.Member.Surname,
                memberFullName = memberFullName,
                memberProfession = authResponse.Member.Profession,
                memberPicture = authResponse.Member.Picture
            )

        if (!saved) {
            SetAnonymous()
            return false
        }

        _state.update { currentState ->
            currentState.copy(
                AuthenticationState =
                    EAuthenticationState.Authenticated,
                MemberId =
                    memberId,
                MemberName =
                    authResponse.Member.Name,
                MemberSurname =
                    authResponse.Member.Surname,
                MemberFullName =
                    memberFullName,
                MemberProfession =
                    authResponse.Member.Profession,
                MemberPicture =
                    authResponse.Member.Picture
            )
        }

        return true
    }

    fun GetMemberId(): Int {
        return _state.value.MemberId
    }

    fun GetRefreshToken(): String? {
        return secureTokenStore
            .ReadTokens()
            ?.RefreshToken
            ?.takeIf { refreshToken ->
                refreshToken.isNotBlank()
            }
    }

    fun SetAnonymous() {
        _state.update { currentState ->
            currentState.copy(
                AuthenticationState =
                    EAuthenticationState.Anonymous,
                MemberId =
                    0,
                MemberName =
                    "",
                MemberSurname =
                    "",
                MemberFullName =
                    "",
                MemberProfession =
                    "",
                MemberPicture =
                    ""
            )
        }
    }

    fun ClearAuthentication(): Boolean {
        val cleared =
            secureTokenStore.Clear()

        SetAnonymous()

        return cleared
    }

    fun Reset() {
        coroutineScope.launch {
            userPreferenceDataStore.Reset()
            secureTokenStore.Clear()

            _state.value =
                UserSessionState(
                    AuthenticationState =
                        EAuthenticationState.Anonymous,
                    MemberId =
                        0
                )
        }
    }

    private fun ObservePreferences() {
        coroutineScope.launch {
            userPreferenceDataStore.SessionState.collect { preferenceState ->
                _state.update { currentState ->
                    currentState.copy(
                        IsInitialized =
                            preferenceState.IsInitialized,
                        ThemeMode =
                            preferenceState.ThemeMode,
                        Language =
                            preferenceState.Language,
                        CountryId =
                            preferenceState.CountryId,
                        CountryName =
                            preferenceState.CountryName,
                        CountryCode =
                            preferenceState.CountryCode,
                        CurrencyId =
                            preferenceState.CurrencyId,
                        CurrencyCode =
                            preferenceState.CurrencyCode,
                        CurrencyName =
                            preferenceState.CurrencyName,
                        CurrencySymbol =
                            preferenceState.CurrencySymbol
                    )
                }
            }
        }
    }
}
