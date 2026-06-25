package com.bulbulustur.android.Application.Controllers

import androidx.lifecycle.viewModelScope
import com.bulbulustur.android.Application.Session.UserSessionManager
import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
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
    val IsLoginSuccessful: Boolean = false
)

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
    private val userSessionManager: UserSessionManager
) : BaseController() {

    private val _state =
        MutableStateFlow(
            LogonControllerState()
        )

    val State: StateFlow<LogonControllerState> =
        _state.asStateFlow()

    /*
     * Android tarafında Login ekranı yerel olarak oluşturulur.
     * Webdeki GET Login action karşılığına ihtiyaç yoktur.
     *
     * İsim paralelliğini korumak için metot bırakılmıştır.
     */
    fun Login() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "Login",
                ErrorMessage = null,
                IsLoginSuccessful = false
            )
        }
    }

    /*
     * Gerçek Authentication API login işlemi.
     */
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

    /*
     * Model üzerinden gerçek Authentication API login işlemi.
     */
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

    /*
     * Kayıt başlangıç ekranı yerel Compose ekranıdır.
     * API işlemi RegisterStartPost içinde bağlanacaktır.
     */
    fun RegisterStart() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "RegisterStart",
                ErrorMessage = null
            )
        }
    }

    /*
     * Sonraki aşamada AuthenticationRepository içindeki
     * first-door endpointine bağlanacaktır.
     */
    fun RegisterStartPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction = "RegisterStartPost"
        )
    }

    /*
     * Kayıt final ekranı yerel Compose ekranıdır.
     * API işlemi RegisterFinalPost içinde bağlanacaktır.
     */
    fun RegisterFinal() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "RegisterFinal",
                ErrorMessage = null
            )
        }
    }

    /*
     * Sonraki aşamada member-insert endpointine
     * gerçek MemberRegisterModel ile bağlanacaktır.
     */
    fun RegisterFinalPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction = "RegisterFinalPost"
        )
    }

    /*
     * Şifremi unuttum ekranı yerel Compose ekranıdır.
     */
    fun ForgotPassword() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "ForgotPassword",
                ErrorMessage = null
            )
        }
    }

    /*
     * Sonraki aşamada forgot endpointine
     * gerçek MemberForgotModel ile bağlanacaktır.
     */
    fun ForgotPasswordPost(
        body: Any? = null
    ) {
        SetPendingOperation(
            currentAction = "ForgotPasswordPost"
        )
    }

    /*
     * Süresi dolmuş işlem ekranı yerel Compose ekranıdır.
     */
    fun Expired() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "Expired",
                ErrorMessage = null
            )
        }
    }

    /*
     * Kayıt kapısı ekranı yerel Compose ekranıdır.
     */
    fun FirstDoor() {
        _state.update { currentState ->
            currentState.copy(
                CurrentAction = "FirstDoor",
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
                IsLoginSuccessful = false
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
                IsLoginSuccessful = false
            )
        }
    }

    private fun ValidateLogin(
        email: String,
        password: String
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

        if (password.isBlank()) {
            return "Şifrenizi girin."
        }

        return null
    }
}