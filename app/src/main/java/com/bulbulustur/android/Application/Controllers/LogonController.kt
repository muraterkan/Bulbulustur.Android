package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberTempFistdoorModel
import com.bulbulustur.android.businesslayer.Core.Model.RevokeTokenRequest
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LogonControllerState(
    val IsLoading: Boolean = false,
    val CurrentAction: String? = null,
    val LastResult: Result<*>? = null,
    val ErrorMessage: String? = null,
    val IsLoginSuccessful: Boolean = false,
    val IsLogoutCompleted: Boolean = false,
    val IsFirstDoorSuccessful: Boolean = false,
    val FirstDoorEmail: String = ""
) {

    val IsLoggingOut: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "LogoutPost"

    val IsSendingFirstDoorEmail: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "FirstDoorPost"
}

sealed interface LogonControllerEvent {

    data object Refresh : LogonControllerEvent

    data class Load(
        val Parameters: Map<String, Any?> = emptyMap()
    ) : LogonControllerEvent

    data class Submit(
        val Body: Any? = null
    ) : LogonControllerEvent
}

class LogonController(
    private val executeService: IExecuteService,
    private val authenticationRepository: IAuthenticationRepository,
    private val memberTempRepository: IMemberTempRepository,
    private val userSessionManager: UserSessionManager
) : BaseController() {

    private val _state =
        MutableStateFlow(
            LogonControllerState()
        )

    val State: StateFlow<LogonControllerState> =
        _state.asStateFlow()

    fun Login() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "Login",
                ErrorMessage = null,
                IsLoginSuccessful = false
            )
        }
    }

    fun LoginPost(
        email: String,
        password: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val model =
            MemberAuthModel(
                Email = email.trim(),
                Password = password
            )

        LoginPost(
            model = model,
            languageId = languageId
        )
    }

    fun LoginPost(
        model: MemberAuthModel,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedModel =
            model.copy(
                Email = model.Email.trim()
            )

        val validationMessage =
            ValidateLogin(
                email = normalizedModel.Email,
                password = normalizedModel.Password
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    CurrentAction = "LoginPost",
                    LastResult = null,
                    ErrorMessage = validationMessage,
                    IsLoginSuccessful = false
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction = "LoginPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.LoginPost"
                ) {
                    authenticationRepository.LoginAsync(
                        languageId = languageId,
                        model = normalizedModel
                    )
                }

            HandleLoginResponse(
                response = response
            )
        }
    }

    fun LogoutPost(
        languageId: Int,
        onCompleted: () -> Unit
    ) {
        if (_state.value.IsLoading) {
            return
        }

        _state.update { currentState ->
            currentState.copy(
                IsLoading = true,
                CurrentAction = "LogoutPost",
                LastResult = null,
                ErrorMessage = null,
                IsLoginSuccessful = false,
                IsLogoutCompleted = false
            )
        }

        val refreshToken =
            userSessionManager.GetRefreshToken()

        val localTokensCleared =
            userSessionManager.ClearAuthentication()

        onCompleted()

        viewModelScope.launch {
            var logoutResponse: Result<Boolean>? =
                null

            try {
                if (!refreshToken.isNullOrBlank()) {
                    val model =
                        RevokeTokenRequest(
                            RefreshToken =
                                refreshToken
                        )

                    logoutResponse =
                        executeService.PostAsync(
                            operationType =
                                "App.Logon.LogoutPost"
                        ) {
                            authenticationRepository.LogoutAsync(
                                languageId =
                                    languageId,
                                model =
                                    model
                            )
                        }
                }
            } finally {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading = false,
                        CurrentAction = "LogoutPost",
                        LastResult = logoutResponse,
                        ErrorMessage =
                            if (localTokensCleared) {
                                null
                            } else {
                                "Oturum bilgileri cihazdan tamamen temizlenemedi."
                            },
                        IsLoginSuccessful = false,
                        IsLogoutCompleted = false
                    )
                }
            }
        }
    }

    fun ConsumeLogoutCompleted() {
        _state.update { currentState ->
            currentState.copy(
                IsLogoutCompleted = false
            )
        }
    }

    fun FirstDoor() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "FirstDoor",
                LastResult = null,
                ErrorMessage = null,
                IsFirstDoorSuccessful = false
            )
        }
    }

    fun FirstDoorPost(
        email: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedEmail =
            email.trim()

        val validationMessage =
            ValidateEmail(
                email = normalizedEmail
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    CurrentAction = "FirstDoorPost",
                    LastResult = null,
                    ErrorMessage = validationMessage,
                    IsFirstDoorSuccessful = false
                )
            }

            return
        }

        val model =
            MemberTempFistdoorModel(
                Email = normalizedEmail
            )

        viewModelScope.launch {
            SetLoading(
                currentAction = "FirstDoorPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.FirstDoorPost"
                ) {
                    memberTempRepository.FirstDoorAsync(
                        languageId = languageId,
                        model = model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading = false,
                        CurrentAction = "FirstDoorPost",
                        LastResult = response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                "Doğrulama e-postası gönderilemedi."
                            },
                        IsFirstDoorSuccessful = false
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    CurrentAction = "FirstDoorPost",
                    LastResult = response,
                    ErrorMessage = null,
                    IsFirstDoorSuccessful = true,
                    FirstDoorEmail = normalizedEmail
                )
            }
        }
    }

    fun ConsumeFirstDoorSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsFirstDoorSuccessful = false
            )
        }
    }

    fun RegisterStart() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "RegisterStart",
                ErrorMessage = null
            )
        }
    }

    fun RegisterFinal() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "RegisterFinal",
                ErrorMessage = null
            )
        }
    }

    fun RegisterFinalPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction = "RegisterFinalPost"
        )
    }

    fun ForgotPassword() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "ForgotPassword",
                ErrorMessage = null
            )
        }
    }

    fun ForgotPasswordPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction = "ForgotPasswordPost"
        )
    }

    fun Expired() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "Expired",
                ErrorMessage = null
            )
        }
    }

    fun ClearError() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage = null
            )
        }
    }

    fun ConsumeLoginSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsLoginSuccessful = false
            )
        }
    }

    private fun HandleLoginResponse(
        response: Result<AuthResponse>
    ) {
        val authResponse =
            response.Data

        if (
            !response.Success ||
            authResponse == null
        ) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    LastResult = response,
                    ErrorMessage =
                        response.Message.ifBlank {
                            "Giriş işlemi başarısız oldu."
                        },
                    IsLoginSuccessful = false
                )
            }

            return
        }

        val sessionSaved =
            userSessionManager.SetAuthenticated(
                authResponse = authResponse
            )

        if (!sessionSaved) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading = false,
                    LastResult = response,
                    ErrorMessage =
                        "Oturum bilgileri güvenli şekilde kaydedilemedi.",
                    IsLoginSuccessful = false
                )
            }

            return
        }

        _state.update { currentState ->
            currentState.copy(
                IsLoading = false,
                LastResult = response,
                ErrorMessage = null,
                IsLoginSuccessful = true
            )
        }
    }

    private fun SetLoading(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading = true,
                CurrentAction = currentAction,
                LastResult = null,
                ErrorMessage = null,
                IsLoginSuccessful = false,
                IsLogoutCompleted = false,
                IsFirstDoorSuccessful = false
            )
        }
    }

    private fun SetPendingOperation(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading = false,
                CurrentAction = currentAction,
                LastResult = null,
                ErrorMessage =
                    "$currentAction işlemi henüz Authentication API'ye bağlanmadı.",
                IsLoginSuccessful = false,
                IsLogoutCompleted = false
            )
        }
    }

    private fun ValidateLogin(
        email: String,
        password: String
    ): String? {
        val emailValidation =
            ValidateEmail(
                email = email
            )

        if (emailValidation != null) {
            return emailValidation
        }

        if (password.isBlank()) {
            return "Şifrenizi girin."
        }

        return null
    }

    private fun ValidateEmail(
        email: String
    ): String? {
        if (email.isBlank()) {
            return "E-posta adresinizi girin."
        }

        val emailParts =
            email.split("@")

        if (
            emailParts.size != 2 ||
            emailParts[0].isBlank() ||
            emailParts[1].isBlank() ||
            !emailParts[1].contains(".")
        ) {
            return "Geçerli bir e-posta adresi girin."
        }

        return null
    }
}