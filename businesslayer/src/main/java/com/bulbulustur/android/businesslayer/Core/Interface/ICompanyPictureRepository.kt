package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPictureUpdateModel

interface ICompanyPictureRepository {

    suspend fun GetCompanyPictureListAsync(): Result<List<CompanyPictureDTO>>

    suspend fun GetCompanyPictureByIdAsync(
        companyPictureId: Int
    ): Result<CompanyPictureUpdateModel?>

    suspend fun GetCompanyPictureByIdExtendedAsync(
        companyPictureId: Int
    ): Result<CompanyPictureDTO?>
}
