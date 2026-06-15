package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyBusinessTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyBusinessTypeUpdateModel

interface ICompanyBusinessTypeRepository {

    suspend fun GetCompanyBusinessTypeListAsync(): Result<List<CompanyBusinessTypeDTO>>

    suspend fun GetCompanyBusinessTypeByIdAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeUpdateModel?>

    suspend fun GetCompanyBusinessTypeByIdExtendedAsync(
        companyBusinessTypeId: Int
    ): Result<CompanyBusinessTypeDTO?>
}
