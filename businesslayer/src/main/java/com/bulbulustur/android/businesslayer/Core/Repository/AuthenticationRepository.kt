package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.Interface.IAuthenticationRepository
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
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
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "login?languageId=$languageId",
            data = model
        )
    }

    override suspend fun RefreshTokenAsync(
        languageId: Int,
        model: RefreshTokenRequest
    ): Result<AuthResponse> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "refresh-token?languageId=$languageId",
            data = model
        )
    }

    override suspend fun LogoutAsync(
        languageId: Int,
        model: RevokeTokenRequest
    ): Result<Boolean> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.AUTHENTICATION_BASE_URL,
            method = "logout?languageId=$languageId",
            data = model
        )
    }
}