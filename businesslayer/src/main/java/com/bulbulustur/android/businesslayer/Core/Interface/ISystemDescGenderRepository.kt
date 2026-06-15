package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescGenderDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescGenderUpdateModel

interface ISystemDescGenderRepository {

    suspend fun GetSystemDescGenderListAsync(): Result<List<SystemDescGenderDTO>>

    suspend fun GetSystemDescGenderByIdAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderUpdateModel?>

    suspend fun GetSystemDescGenderByIdExtendedAsync(
        systemDescGenderId: Int
    ): Result<SystemDescGenderDTO?>
}
