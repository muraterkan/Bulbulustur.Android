package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberPhoneDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberPhoneInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberPhoneUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberPhoneRepository {

    suspend fun GetMemberPhonesAsync(languageId: Int, memberId: Int, count: Int = 100): Result<List<MemberPhoneDTO>>

    suspend fun GetMemberPhoneByIdAsync(languageId: Int, memberPhoneId: Int, memberId: Int): Result<MemberPhoneUpdateModel?>

    suspend fun GetMemberPhoneByIdExtendedAsync(languageId: Int, memberPhoneId: Int, memberId: Int): Result<MemberPhoneDTO?>

    suspend fun InsertAsync(languageId: Int, model: MemberPhoneInsertModel): Result<Int>

    suspend fun UpdateAsync(languageId: Int, model: MemberPhoneUpdateModel): Result<Unit>

    suspend fun DeleteAsync(languageId: Int, phoneId: Int, memberId: Int): Result<Unit>

    suspend fun GetMemberPrimaryPhoneByIdAsync(languageId: Int, memberId: Int): Result<MemberPhoneUpdateModel?>

    suspend fun SendVerificationSmsAsync(languageId: Int, memberPhoneId: Int, memberId: Int): Result<Unit>

    suspend fun VerifyPhoneAsync(languageId: Int, model: MemberPhoneUpdateModel): Result<Unit>
}