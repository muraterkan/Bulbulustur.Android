package com.bulbulustur.android.businesslayer.Core.Repository import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient

class CompanyPictureRepository(
    private val apiClient: ApiClient
) : ICompanyPictureRepository {

    override suspend fun GetCompanyPictureListAsync(): Result<List<CompanyPictureDTO>> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPictureByIdAsync(
        companyPictureId: Int
    ): Result<CompanyPictureUpdateModel?> {
        TODO("Not implemented yet")
    }

    override suspend fun GetCompanyPictureByIdExtendedAsync(
        companyPictureId: Int
    ): Result<CompanyPictureDTO?> {
        TODO("Not implemented yet")
    }
}
