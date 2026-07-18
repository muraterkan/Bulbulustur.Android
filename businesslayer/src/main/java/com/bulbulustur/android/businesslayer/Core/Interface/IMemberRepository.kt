package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.MemberDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.MemberInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.MemberRegisterModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateAddressModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateBirthDateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateGenderModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.MemberUpdateTcknModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Model.ChangePasswordAsyncModel
import com.bulbulustur.android.businesslayer.Core.Model.ChangeMailModel

interface IMemberRepository {

    suspend fun GetMemberListAsync(): Result<List<MemberDTO>>

    suspend fun GetMemberByIdAsync(languageId: Int, memberId: Int): Result<MemberUpdateModel?>

    suspend fun GetMemberByIdExtendedAsync(languageId: Int, memberId: Int): Result<MemberDTO?>

    suspend fun InsertAsync(model: MemberInsertModel): Result<Unit>

    suspend fun InsertAsync(languageId: Int, model: MemberRegisterModel): Result<MemberInsertModel>

    suspend fun UpdateAsync(model: MemberUpdateModel): Result<MemberUpdateModel>

    suspend fun MemberUpdateGenderAsync(model: MemberUpdateGenderModel): Result<MemberUpdateGenderModel>

    suspend fun MemberUpdateBirthDateAsync(model: MemberUpdateBirthDateModel): Result<MemberUpdateBirthDateModel>

    suspend fun MemberUpdateAddressAsync(model: MemberUpdateAddressModel): Result<MemberUpdateAddressModel>

    suspend fun MemberUpdateTcknAsync(model: MemberUpdateTcknModel): Result<MemberUpdateTcknModel>


    suspend fun ChangePasswordAsync(languageId: Int, model: ChangePasswordAsyncModel): Result<Unit>

    suspend fun SendEmailChangingRequestAsync(model: ChangeMailModel): Result<ChangeMailModel>

    suspend fun DeleteAsync(memberId: Int): Result<Unit>
}