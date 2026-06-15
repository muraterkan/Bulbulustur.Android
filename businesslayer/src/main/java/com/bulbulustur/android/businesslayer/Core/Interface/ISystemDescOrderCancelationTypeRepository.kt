package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescOrderCancelationTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescOrderCancelationTypeUpdateModel

interface ISystemDescOrderCancelationTypeRepository {

    suspend fun GetSystemDescOrderCancelationTypeListAsync(): Result<List<SystemDescOrderCancelationTypeDTO>>

    suspend fun GetSystemDescOrderCancelationTypeByIdAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeUpdateModel?>

    suspend fun GetSystemDescOrderCancelationTypeByIdExtendedAsync(
        systemDescOrderCancelationTypeId: Int
    ): Result<SystemDescOrderCancelationTypeDTO?>
}
