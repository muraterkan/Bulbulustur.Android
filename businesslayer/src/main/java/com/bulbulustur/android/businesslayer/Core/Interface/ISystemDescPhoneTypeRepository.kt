package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescPhoneTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescPhoneTypeUpdateModel

interface ISystemDescPhoneTypeRepository {

    suspend fun GetSystemDescPhoneTypeListAsync(): Result<List<SystemDescPhoneTypeDTO>>

    suspend fun GetSystemDescPhoneTypeByIdAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeUpdateModel?>

    suspend fun GetSystemDescPhoneTypeByIdExtendedAsync(
        systemDescPhoneTypeId: Int
    ): Result<SystemDescPhoneTypeDTO?>
}
