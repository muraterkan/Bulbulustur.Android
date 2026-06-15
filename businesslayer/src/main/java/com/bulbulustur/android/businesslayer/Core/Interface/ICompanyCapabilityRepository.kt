package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyCapabilityDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyCapabilityUpdateModel

interface ICompanyCapabilityRepository {

    suspend fun GetCompanyCapabilityListAsync(): Result<List<CompanyCapabilityDTO>>

    suspend fun GetCompanyCapabilityByIdAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityUpdateModel?>

    suspend fun GetCompanyCapabilityByIdExtendedAsync(
        companyCapabilityId: Int
    ): Result<CompanyCapabilityDTO?>
}
