package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.GoogleLoginRequest
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberForgotModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberSetPasswordModel
import com.bulbulustur.android.businesslayer.Core.Model.RefreshTokenRequest
import com.bulbulustur.android.businesslayer.Core.Model.RevokeTokenRequest
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class AuthenticationRepository(
    private val apiClient: ApiClient = ApiClient
) : IAuthenticationRepository {

    override suspend fun LoginAsync(
        languageId: Int,
        model: MemberAuthModel
    ): Result<AuthResponse> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.AUTHENTICATION_BASE_URL,
            method =
                "login?languageId=$languageId",
            data =
                model
        )
    }

    override suspend fun GoogleLoginAsync(
        model: GoogleLoginRequest
    ): Result<AuthResponse> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.AUTHENTICATION_BASE_URL,
            method =
                "google-login",
            data =
                model
        )
    }

    override suspend fun RefreshTokenAsync(
        languageId: Int,
        model: RefreshTokenRequest
    ): Result<AuthResponse> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.AUTHENTICATION_BASE_URL,
            method =
                "refresh-token?languageId=$languageId",
            data =
                model
        )
    }

    override suspend fun LogoutAsync(
        languageId: Int,
        model: RevokeTokenRequest
    ): Result<Boolean> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.AUTHENTICATION_BASE_URL,
            method =
                "logout?languageId=$languageId",
            data =
                model
        )
    }

    override suspend fun SendLinkForForgotAsync(
        languageId: Int,
        model: MemberForgotModel
    ): Result<MemberDTO> {
        return apiClient.PostAsync(
            baseUrl =
                ApiRoutes.AUTHENTICATION_BASE_URL,
            method =
                "forgot?languageId=$languageId",
            data =
                model
        )
    }

    override suspend fun UpdatePasswordAsync(languageId: Int, model: MemberSetPasswordModel): Result<MemberDTO>
    {
        val requestModel = model.copy(LanguageId = languageId)

        return apiClient.PostAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "update-password",
            data = requestModel
        )
    }
}