package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IMemberRepository {

    suspend fun GetMemberListAsync(): Result<List<MemberDTO>>

    suspend fun GetMemberByIdAsync(languageId: Int, memberId: Int): Result<MemberUpdateModel?>

    suspend fun GetMemberByIdExtendedAsync(languageId: Int, memberId: Int): Result<MemberDTO?>

    suspend fun InsertAsync(model: MemberInsertModel): Result<Unit>

    suspend fun InsertAsync(languageId: Int, model: MemberRegisterModel): Result<MemberInsertModel>

    suspend fun UpdateAsync(model: MemberUpdateModel): Result<Unit>

    suspend fun SetContactPreferenceAsync(model: MemberUpdateModel): Result<Unit>

    suspend fun DeleteAsync(memberId: Int): Result<Unit>
}