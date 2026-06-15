package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescNumberOfEmployeeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescNumberOfEmployeeUpdateModel

interface ISystemDescNumberOfEmployeeRepository {

    suspend fun GetSystemDescNumberOfEmployeeListAsync(): Result<List<SystemDescNumberOfEmployeeDTO>>

    suspend fun GetSystemDescNumberOfEmployeeByIdAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeUpdateModel?>

    suspend fun GetSystemDescNumberOfEmployeeByIdExtendedAsync(
        systemDescNumberOfEmployeeId: Int
    ): Result<SystemDescNumberOfEmployeeDTO?>
}
