package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoDesiDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoDesiUpdateModel

interface ISystemDescCargoDesiRepository {

    suspend fun GetSystemDescCargoDesiListAsync(): Result<List<SystemDescCargoDesiDTO>>

    suspend fun GetSystemDescCargoDesiByIdAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiUpdateModel?>

    suspend fun GetSystemDescCargoDesiByIdExtendedAsync(
        systemDescCargoDesiId: Int
    ): Result<SystemDescCargoDesiDTO?>
}
