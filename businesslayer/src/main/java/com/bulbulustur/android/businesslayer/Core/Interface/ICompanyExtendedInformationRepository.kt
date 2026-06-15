package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyExtendedInformationDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyExtendedInformationUpdateModel

interface ICompanyExtendedInformationRepository {

    suspend fun GetCompanyExtendedInformationListAsync(): Result<List<CompanyExtendedInformationDTO>>

    suspend fun GetCompanyExtendedInformationByIdAsync(
        extendedInformationId: Int
    ): Result<CompanyExtendedInformationUpdateModel?>

    suspend fun GetCompanyExtendedInformationByIdExtendedAsync(
        extendedInformationId: Int
    ): Result<CompanyExtendedInformationDTO?>
}
