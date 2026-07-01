package com.bulbulustur.android.Application.Session

import com.bulbulustur.android.Application.Datastore.UserPreferenceDataStore
import com.bulbulustur.android.businesslayer.Core.Enums.EApplicationLanguage
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
                themeMode = themeMode
            )
        }
    }

    fun SetLanguage(
        language: EApplicationLanguage
    ) {
        coroutineScope.launch {
            userPreferenceDataStore.SetLanguage(
                language = language
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
                        0
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
                    value =
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
                        storedTokens.MemberId
                )
            }
        }
    }

    fun SetAuthenticated(
        authResponse: AuthResponse
    ): Boolean {
        val memberId =
            JwtMemberIdParser.Parse(
                token =
                    authResponse.Token
            )

        if (memberId <= 0) {
            SetAnonymous()
            return false
        }

        val saved =
            secureTokenStore.SaveTokens(
                accessToken =
                    authResponse.Token,
                refreshToken =
                    authResponse.RefreshToken,
                expiration =
                    authResponse.Expiration,
                memberId =
                    memberId
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
                    memberId
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
                    0
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
            userPreferenceDataStore.SessionState.collect {
                    preferenceState ->

                _state.update { currentState ->
                    currentState.copy(
                        IsInitialized =
                            preferenceState.IsInitialized,
                        ThemeMode =
                            preferenceState.ThemeMode,
                        Language =
                            preferenceState.Language
                    )
                }
            }
        }
    }
}