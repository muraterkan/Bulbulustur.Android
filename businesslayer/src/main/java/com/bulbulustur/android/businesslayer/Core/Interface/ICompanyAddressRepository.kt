package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyAddressDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyAddressUpdateModel

interface ICompanyAddressRepository {

    suspend fun GetCompanyAddressListAsync(): Result<List<CompanyAddressDTO>>

    suspend fun GetCompanyAddressByIdAsync(
        companyAddressId: Int
    ): Result<CompanyAddressUpdateModel?>

    suspend fun GetCompanyAddressByIdExtendedAsync(
        companyAddressId: Int
    ): Result<CompanyAddressDTO?>
}
