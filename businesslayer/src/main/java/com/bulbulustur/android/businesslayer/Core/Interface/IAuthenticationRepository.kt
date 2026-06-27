package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.AuthResponse
import com.bulbulustur.android.businesslayer.Core.Model.MemberAuthModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberForgotModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberSetPasswordModel
import com.bulbulustur.android.businesslayer.Core.Model.RefreshTokenRequest
import com.bulbulustur.android.businesslayer.Core.Model.RevokeTokenRequest
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IAuthenticationRepository {

    suspend fun LoginAsync(
        languageId: Int,
        model: MemberAuthModel
    ): Result<AuthResponse>

    suspend fun RefreshTokenAsync(
        languageId: Int,
        model: RefreshTokenRequest
    ): Result<AuthResponse>

    suspend fun LogoutAsync(
        languageId: Int,
        model: RevokeTokenRequest
    ): Result<Boolean>

    suspend fun SendLinkForForgotAsync(
        languageId: Int,
        model: MemberForgotModel
    ): Result<MemberDTO>

    suspend fun UpdatePasswordAsync(
        languageId: Int,
        model: MemberSetPasswordModel
    ): Result<MemberDTO>
}