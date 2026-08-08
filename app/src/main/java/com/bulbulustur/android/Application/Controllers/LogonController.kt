package com.bulbulustur.android.Application.Controllers

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Localization.BBLocalization
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.businesslayer.Core.DTO.MemberTempDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberRepository
import com.bulbulustur.android.businesslayer.Core.Interface.IMemberTempRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.GoogleLoginRequest
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberForgotModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberSetPasswordModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberTempFistdoorModel
import com.bulbulustur.android.businesslayer.Core.Model.RevokeTokenRequest
import com.bulbulustur.android.businesslayer.Core.Util.Execute.IExecuteService
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
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
    val FirstDoorEmail: String = "",

    val IsForgotPasswordSuccessful: Boolean = false,
    val ForgotPasswordEmail: String = "",
    val ForgotPasswordMessage: String? = null,

    val IsSetNewPasswordSuccessful: Boolean = false,
    val SetNewPasswordMessage: String? = null,

    val IsMemberTempLoading: Boolean = false,
    val IsMemberTempLoaded: Boolean = false,
    val MemberTemp: MemberTempDTO? = null,

    val IsRegisterSuccessful: Boolean = false,
    val RegisteredMember: MemberInsertModel? = null,
    val RegisteredEmail: String = ""
) {

    val IsLoggingOut: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "LogoutPost"

    val IsSendingFirstDoorEmail: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "FirstDoorPost"

    val IsSendingForgotPasswordLink: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "ForgotPasswordPost"

    val IsUpdatingPassword: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "SetNewPasswordPost"

    val IsGoogleLoginLoading: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "GoogleLoginPost"

    val IsRegistering: Boolean
        get() =
            IsLoading &&
                    CurrentAction == "RegisterPost"
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
    private val memberRepository: IMemberRepository,
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
                CurrentAction =
                    "Login",
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false
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
                Email =
                    email.trim(),
                Password =
                    password
            )

        LoginPost(
            model =
                model,
            languageId =
                languageId
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
                Email =
                    model.Email.trim()
            )

        val validationMessage =
            ValidateLogin(
                email =
                    normalizedModel.Email,
                password =
                    normalizedModel.Password
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "LoginPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "LoginPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.LoginPost"
                ) {
                    authenticationRepository.LoginAsync(
                        languageId =
                            languageId,
                        model =
                            normalizedModel
                    )
                }

            HandleLoginResponse(
                response =
                    response
            )
        }
    }

    fun GoogleLoginPost(
        idToken: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedIdToken =
            idToken.trim()

        if (normalizedIdToken.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GoogleLoginPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "e7b7b25c-bfe3-45dc-955f-4e92fc130a00", fallback = "Google kimlik doğrulama bilgisi alınamadı."),
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        val model =
            GoogleLoginRequest(
                IdToken =
                    normalizedIdToken,
                LanguageId =
                    languageId,
                DeviceType =
                    "Android",
                Platform =
                    "Android",
                Browser =
                    "Mobile App",
                IPAddress =
                    ""
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "GoogleLoginPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.GoogleLoginPost"
                ) {
                    authenticationRepository.GoogleLoginAsync(
                        model =
                            model
                    )
                }

            HandleLoginResponse(
                response =
                    response
            )
        }
    }

    fun SetGoogleLoginError(
        message: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading = false,
                CurrentAction = "GoogleLoginPost",
                LastResult = null,
                ErrorMessage =
                    message.ifBlank {
                        BBLocalization.Current.Get(key = "18b3f4f4-b103-445f-8d84-796123cc4ced", fallback = "Google ile giriş başlatılamadı.")
                    },
                IsLoginSuccessful = false
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
                IsLoading =
                    true,
                CurrentAction =
                    "LogoutPost",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false
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
                        IsLoading =
                            false,
                        CurrentAction =
                            "LogoutPost",
                        LastResult =
                            logoutResponse,
                        ErrorMessage =
                            if (localTokensCleared) {
                                null
                            } else {
                                BBLocalization.Current.Get(key = "778ea177-be8b-451d-b8ba-1f0c6fa0219b", fallback = "Oturum bilgileri cihazdan tamamen temizlenemedi.")
                            },
                        IsLoginSuccessful =
                            false,
                        IsLogoutCompleted =
                            false
                    )
                }
            }
        }
    }

    fun ConsumeLogoutCompleted() {
        _state.update { currentState ->
            currentState.copy(
                IsLogoutCompleted =
                    false
            )
        }
    }

    fun FirstDoor() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "FirstDoor",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsFirstDoorSuccessful =
                    false
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
                email =
                    normalizedEmail
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "FirstDoorPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsFirstDoorSuccessful =
                        false
                )
            }

            return
        }

        val model =
            MemberTempFistdoorModel(
                Email =
                    normalizedEmail
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "FirstDoorPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.FirstDoorPost"
                ) {
                    memberTempRepository.FirstDoorAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "FirstDoorPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                BBLocalization.Current.Get(key = "8e840fd7-4b80-4b54-a174-36cc36b14401", fallback = "Doğrulama e-postası gönderilemedi.")
                            },
                        IsFirstDoorSuccessful =
                            false
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "FirstDoorPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsFirstDoorSuccessful =
                        true,
                    FirstDoorEmail =
                        normalizedEmail
                )
            }
        }
    }

    fun ConsumeFirstDoorSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsFirstDoorSuccessful =
                    false
            )
        }
    }

    fun GetMemberTempByActivationCode(
        activationCode: String,
        languageId: Int
    ) {
        if (
            _state.value.IsLoading ||
            _state.value.IsMemberTempLoading
        ) {
            return
        }

        val normalizedActivationCode =
            activationCode.trim()

        if (normalizedActivationCode.isBlank()) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        null,
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "54fb6609-058f-4096-9c36-7db3b45abbe1", fallback = "Kayıt bağlantısı geçersiz."),
                    IsMemberTempLoading =
                        false,
                    IsMemberTempLoaded =
                        false,
                    MemberTemp =
                        null
                )
            }

            return
        }

        viewModelScope.launch {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        null,
                    ErrorMessage =
                        null,
                    IsMemberTempLoading =
                        true,
                    IsMemberTempLoaded =
                        false,
                    MemberTemp =
                        null
                )
            }

            val response =
                executeService.GetAsync {
                    memberTempRepository
                        .GetMemberTempByActivationCodeAsync(
                            languageId =
                                languageId,
                            uuid =
                                normalizedActivationCode
                        )
                }

            val memberTemp =
                response.Data

            if (
                !response.Success ||
                memberTemp == null ||
                memberTemp.Email.isBlank() ||
                memberTemp.ActivationCode.isBlank()
            ) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "GetMemberTempByActivationCode",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                BBLocalization.Current.Get(key = "6cda054a-c825-4c87-bba1-d5f8be2e92a2", fallback = "Kayıt bağlantısı geçersiz veya süresi dolmuş.")
                            },
                        IsMemberTempLoading =
                            false,
                        IsMemberTempLoaded =
                            false,
                        MemberTemp =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "GetMemberTempByActivationCode",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsMemberTempLoading =
                        false,
                    IsMemberTempLoaded =
                        true,
                    MemberTemp =
                        memberTemp
                )
            }
        }
    }

    fun ClearMemberTemp() {
        _state.update { currentState ->
            currentState.copy(
                IsMemberTempLoading =
                    false,
                IsMemberTempLoaded =
                    false,
                MemberTemp =
                    null,
                ErrorMessage =
                    null,
                IsRegisterSuccessful =
                    false,
                RegisteredMember =
                    null
            )
        }
    }

    fun RegisterStart() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    "RegisterStart",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsRegisterSuccessful =
                    false,
                RegisteredMember =
                    null,
                RegisteredEmail =
                    ""
            )
        }
    }

    fun RegisterPost(
        model: MemberRegisterModel,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedModel =
            model.copy(
                Email =
                    model.Email.trim(),
                Name =
                    model.Name.trim(),
                Surname =
                    model.Surname.trim(),
                ActivationCode =
                    model.ActivationCode.trim(),
                Uuid =
                    model.Uuid.trim(),
                LoginProvider =
                    model.LoginProvider.trim().ifBlank {
                        "Email"
                    },
                LanguageId =
                    languageId
            )

        val validationMessage =
            ValidateRegister(
                model =
                    normalizedModel
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "RegisterPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsRegisterSuccessful =
                        false,
                    RegisteredMember =
                        null,
                    RegisteredEmail =
                        ""
                )
            }

            return
        }

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "RegisterPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.RegisterPost"
                ) {
                    memberRepository.InsertAsync(
                        languageId =
                            languageId,
                        model =
                            normalizedModel
                    )
                }

            val registeredMember =
                response.Data

            if (
                !response.Success ||
                registeredMember == null
            ) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "RegisterPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                BBLocalization.Current.Get(key = "cff38eaa-8e95-4b48-85c5-65476580dc64", fallback = "Üyelik oluşturulamadı.")
                            },
                        IsRegisterSuccessful =
                            false,
                        RegisteredMember =
                            null,
                        RegisteredEmail =
                            ""
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "RegisterPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsRegisterSuccessful =
                        true,
                    RegisteredMember =
                        registeredMember,
                    RegisteredEmail =
                        normalizedModel.Email
                )
            }
        }
    }

    fun ConsumeRegisterSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsRegisterSuccessful =
                    false
            )
        }
    }

    fun RegisterFinal() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "RegisterFinal",
                ErrorMessage =
                    null
            )
        }
    }

    fun ForgotPassword() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    "ForgotPassword",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordEmail =
                    "",
                ForgotPasswordMessage =
                    null
            )
        }
    }

    fun ForgotPasswordPost(
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
                email =
                    normalizedEmail
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ForgotPasswordPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsForgotPasswordSuccessful =
                        false,
                    ForgotPasswordMessage =
                        null
                )
            }

            return
        }

        val model =
            MemberForgotModel(
                Email =
                    normalizedEmail
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "ForgotPasswordPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.ForgotPasswordPost"
                ) {
                    authenticationRepository.SendLinkForForgotAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "ForgotPasswordPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                BBLocalization.Current.Get(key = "d5107145-1fda-4354-a9bb-8945b93ed60c", fallback = "Şifre yenileme bağlantısı gönderilemedi.")
                            },
                        IsForgotPasswordSuccessful =
                            false,
                        ForgotPasswordEmail =
                            normalizedEmail,
                        ForgotPasswordMessage =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "ForgotPasswordPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsForgotPasswordSuccessful =
                        true,
                    ForgotPasswordEmail =
                        normalizedEmail,
                    ForgotPasswordMessage =
                        response.Message.ifBlank {
                            BBLocalization.Current.Get(key = "d94407f7-56c6-4fbb-b9cf-673d883a3762", fallback = "Şifre yenileme bağlantısı e-posta adresinize gönderildi.")
                        }
                )
            }
        }
    }

    fun SetNewPassword() {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    "SetNewPassword",
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun SetNewPasswordPost(
        activationCode: String,
        newPassword: String,
        reNewPassword: String,
        languageId: Int
    ) {
        if (_state.value.IsLoading) {
            return
        }

        val normalizedActivationCode =
            activationCode.trim()

        val validationMessage =
            ValidateSetNewPassword(
                activationCode =
                    normalizedActivationCode,
                newPassword =
                    newPassword,
                reNewPassword =
                    reNewPassword
            )

        if (validationMessage != null) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SetNewPasswordPost",
                    LastResult =
                        null,
                    ErrorMessage =
                        validationMessage,
                    IsSetNewPasswordSuccessful =
                        false,
                    SetNewPasswordMessage =
                        null
                )
            }

            return
        }

        val model =
            MemberSetPasswordModel(
                NewPassword =
                    newPassword,
                ReNewPassword =
                    reNewPassword,
                ActivationCode =
                    normalizedActivationCode,
                DeviceType =
                    "Android",
                IPAddress =
                    "",
                Browser =
                    "",
                Platform =
                    "Android",
                Location =
                    "",
                LanguageId =
                    languageId
            )

        viewModelScope.launch {
            SetLoading(
                currentAction =
                    "SetNewPasswordPost"
            )

            val response =
                executeService.PostAsync(
                    operationType =
                        "App.Logon.SetNewPasswordPost"
                ) {
                    authenticationRepository.UpdatePasswordAsync(
                        languageId =
                            languageId,
                        model =
                            model
                    )
                }

            if (!response.Success) {
                _state.update { currentState ->
                    currentState.copy(
                        IsLoading =
                            false,
                        CurrentAction =
                            "SetNewPasswordPost",
                        LastResult =
                            response,
                        ErrorMessage =
                            response.Message.ifBlank {
                                BBLocalization.Current.Get(key = "84c8de86-66bc-4dd5-aacb-517820fe0d71", fallback = "Şifreniz güncellenemedi.")
                            },
                        IsSetNewPasswordSuccessful =
                            false,
                        SetNewPasswordMessage =
                            null
                    )
                }

                return@launch
            }

            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    CurrentAction =
                        "SetNewPasswordPost",
                    LastResult =
                        response,
                    ErrorMessage =
                        null,
                    IsSetNewPasswordSuccessful =
                        true,
                    SetNewPasswordMessage =
                        response.Message.ifBlank {
                            BBLocalization.Current.Get(key = "903b84fd-627e-43db-9b58-aa4c6f857eab", fallback = "Şifreniz başarıyla güncellendi.")
                        }
                )
            }
        }
    }

    fun Expired() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction =
                    "Expired",
                ErrorMessage =
                    null
            )
        }
    }

    fun ClearError() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null
            )
        }
    }

    fun ClearForgotPasswordFeedback() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordMessage =
                    null
            )
        }
    }

    fun ClearSetNewPasswordFeedback() {
        _state.update { currentState ->
            currentState.copy(
                ErrorMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun ConsumeSetNewPasswordSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null
            )
        }
    }

    fun ConsumeLoginSuccess() {
        _state.update { currentState ->
            currentState.copy(
                IsLoginSuccessful =
                    false
            )
        }
    }

    private fun HandleLoginResponse(
        response: Result<AuthResponse>
    ) {
        val authResponse =
            response.Data



        Log.d(
            "LogonController",
            """
        HandleLoginResponse:
        Success=${response.Success}
        Message=${response.Message}
        HasData=${authResponse != null}
        HasToken=${!authResponse?.Token.isNullOrBlank()}
        HasRefreshToken=${!authResponse?.RefreshToken.isNullOrBlank()}
        Expiration=${authResponse?.Expiration}
        """.trimIndent()
        )





        if (
            !response.Success ||
            authResponse == null
        ) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    LastResult =
                        response,
                    ErrorMessage =
                        response.Message.ifBlank {
                            BBLocalization.Current.Get(key = "9292c9d6-cfd3-496f-a98a-bd2ae649b96a", fallback = "Giriş işlemi başarısız oldu.")
                        },
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        val sessionSaved =
            userSessionManager.SetAuthenticated(
                authResponse =
                    authResponse
            )

        if (!sessionSaved) {
            _state.update { currentState ->
                currentState.copy(
                    IsLoading =
                        false,
                    LastResult =
                        response,
                    ErrorMessage =
                        BBLocalization.Current.Get(key = "775bcd8c-188b-4e34-a34f-249978857c13", fallback = "Oturum bilgileri güvenli şekilde kaydedilemedi."),
                    IsLoginSuccessful =
                        false
                )
            }

            return
        }

        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                LastResult =
                    response,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    true
            )
        }
    }

    private fun SetLoading(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    true,
                CurrentAction =
                    currentAction,
                LastResult =
                    null,
                ErrorMessage =
                    null,
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false,
                IsFirstDoorSuccessful =
                    false,
                IsForgotPasswordSuccessful =
                    false,
                ForgotPasswordMessage =
                    null,
                IsSetNewPasswordSuccessful =
                    false,
                SetNewPasswordMessage =
                    null,
                IsRegisterSuccessful =
                    false,
                RegisteredMember =
                    null,
                RegisteredEmail =
                    ""
            )
        }
    }

    private fun SetPendingOperation(
        currentAction: String
    ) {
        _state.update { currentState ->
            currentState.copy(
                IsLoading =
                    false,
                CurrentAction =
                    currentAction,
                LastResult =
                    null,
                ErrorMessage =
                    "$currentAction işlemi henüz Authentication API'ye bağlanmadı.",
                IsLoginSuccessful =
                    false,
                IsLogoutCompleted =
                    false
            )
        }
    }

    private fun ValidateRegister(
        model: MemberRegisterModel
    ): String? {
        val emailValidation =
            ValidateEmail(
                email =
                    model.Email
            )

        if (emailValidation != null) {
            return emailValidation
        }

        if (model.Name.isBlank()) {
            return BBLocalization.Current.Get(key = "fbb6bd43-f7c0-458c-a2c7-9fd93b72893d", fallback = "Adınızı girin.")
        }

        if (model.Surname.isBlank()) {
            return BBLocalization.Current.Get(key = "56fcc8b3-06bb-4dfc-8a89-c2792879d0ac", fallback = "Soyadınızı girin.")
        }

        if (model.CountryId <= 0) {
            return BBLocalization.Current.Get(key = "baf6fc11-018f-4241-aa11-c726c07bb26c", fallback = "Ülke seçin.")
        }

        if (model.CityId <= 0) {
            return BBLocalization.Current.Get(key = "a63fde8b-360d-4778-9454-c588680b0b23", fallback = "Şehir seçin.")
        }

        if (model.Password.length < 8) {
            return BBLocalization.Current.Get(key = "8e93a610-e453-4461-befd-6f809bb3ce62", fallback = "Şifreniz en az 8 karakter olmalıdır.")
        }

        if (model.Password != model.PasswordAgain) {
            return BBLocalization.Current.Get(key = "b7421a1d-7c44-4d9f-9ab1-58e3e0ad4f91", fallback = "Şifreler birbiriyle eşleşmiyor.")
        }

        if (model.ActivationCode.isBlank()) {
            return BBLocalization.Current.Get(key = "ea2e0332-af39-4955-93da-1ed8a2f95497", fallback = "Kayıt doğrulama bilgisi bulunamadı.")
        }

        if (model.Uuid.isBlank()) {
            return BBLocalization.Current.Get(key = "54fb6609-058f-4096-9c36-7db3b45abbe1", fallback = "Kayıt bağlantısı geçersiz.")
        }

        return null
    }

    private fun ValidateLogin(
        email: String,
        password: String
    ): String? {
        val emailValidation =
            ValidateEmail(
                email =
                    email
            )

        if (emailValidation != null) {
            return emailValidation
        }

        if (password.isBlank()) {
            return BBLocalization.Current.Get(key = "9288dd27-98b7-4b3c-814f-0bc3d2f89ee9", fallback = "Şifrenizi girin.")
        }

        return null
    }

    private fun ValidateEmail(
        email: String
    ): String? {
        if (email.isBlank()) {
            return BBLocalization.Current.Get(key = "e6f44bb3-1a51-4db7-86d7-ab38d008440a", fallback = "E-posta adresinizi girin.")
        }

        if (email.contains(" ")) {
            return BBLocalization.Current.Get(key = "1a29428d-6472-4e3c-ba56-4e07d11cc334", fallback = "")
        }

        val emailParts =
            email.split("@")

        if (
            emailParts.size != 2 ||
            emailParts[0].isBlank() ||
            emailParts[1].isBlank() ||
            !emailParts[1].contains(".")
        ) {
            return BBLocalization.Current.Get(key = "1a29428d-6472-4e3c-ba56-4e07d11cc334", fallback = "")
        }

        return null
    }

    private fun ValidateSetNewPassword(
        activationCode: String,
        newPassword: String,
        reNewPassword: String
    ): String? {
        if (activationCode.isBlank()) {
            return BBLocalization.Current.Get(key = "e923bdf1-23aa-4920-bb3c-40861e43938b", fallback = "Şifre yenileme bağlantısı geçersiz veya süresi dolmuş.")
        }

        if (newPassword.isBlank()) {
            return BBLocalization.Current.Get(key = "f5a11c37-9ee5-47c0-a178-6343ee5bc63d", fallback = "Yeni şifrenizi girin.")
        }

        if (
            newPassword.length < 8 ||
            newPassword.length > 16
        ) {
            return BBLocalization.Current.Get(key = "27fd8e90-9c8b-4b1e-b61b-90e9d566fbc4", fallback = "")
        }

        if (reNewPassword.isBlank()) {
            return BBLocalization.Current.Get(key = "f5a11c37-9ee5-47c0-a178-6343ee5bc63d", fallback = "Yeni şifrenizi tekrar girin.")
        }

        if (
            reNewPassword.length < 8 ||
            reNewPassword.length > 16
        ) {
            return BBLocalization.Current.Get(key = "27fd8e90-9c8b-4b1e-b61b-90e9d566fbc4", fallback = "")
        }

        if (newPassword != reNewPassword) {
            return BBLocalization.Current.Get(key = "b7421a1d-7c44-4d9f-9ab1-58e3e0ad4f91", fallback = "Şifreler birbiriyle eşleşmiyor.")
        }

        return null
    }
}