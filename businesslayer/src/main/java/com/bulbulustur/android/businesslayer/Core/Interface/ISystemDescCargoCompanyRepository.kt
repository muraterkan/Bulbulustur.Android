package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyUpdateModel

interface ISystemDescCargoCompanyRepository {

    suspend fun GetSystemDescCargoCompanyListAsync(): Result<List<SystemDescCargoCompanyDTO>>

    suspend fun GetSystemDescCargoCompanyByIdAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyUpdateModel?>

    suspend fun GetSystemDescCargoCompanyByIdExtendedAsync(
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyDTO?>
}
