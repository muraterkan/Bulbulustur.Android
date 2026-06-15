package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescMemberTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescMemberTypeUpdateModel

interface ISystemDescMemberTypeRepository {

    suspend fun GetSystemDescMemberTypeListAsync(): Result<List<SystemDescMemberTypeDTO>>

    suspend fun GetSystemDescMemberTypeByIdAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeUpdateModel?>

    suspend fun GetSystemDescMemberTypeByIdExtendedAsync(
        systemDescMemberTypeId: Int
    ): Result<SystemDescMemberTypeDTO?>
}
