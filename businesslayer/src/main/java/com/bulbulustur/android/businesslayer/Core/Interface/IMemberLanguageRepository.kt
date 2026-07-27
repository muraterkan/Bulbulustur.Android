package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberLanguageDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberLanguageInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberLanguageUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberLanguageRepository {

    suspend fun GetAccountLanguagesAsync(
        memberId: Int,
        count: Int
    ): Result<List<MemberLanguageDTO>>

    suspend fun GetAccountLanguageByIdAsync(
        memberId: Int,
        memberLanguageId: Int
    ): Result<MemberLanguageUpdateModel?>

    suspend fun InsertAccountLanguageAsync(
        memberId: Int,
        model: MemberLanguageInsertModel
    ): Result<Unit>

    suspend fun UpdateAccountLanguageAsync(
        memberId: Int,
        model: MemberLanguageUpdateModel
    ): Result<Unit>

    suspend fun DeleteAccountLanguageAsync(
        memberLanguageId: Int
    ): Result<Unit>
}
