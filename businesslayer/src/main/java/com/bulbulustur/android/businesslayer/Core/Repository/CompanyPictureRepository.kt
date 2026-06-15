package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPictureDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyPictureRepository
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyPictureRepository(
    private val apiClient: ApiClient = ApiClient
) : ICompanyPictureRepository {

    override suspend fun GetCompanyPictureListAsync(): Result<List<CompanyPictureDTO>> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPictureListAsync"
        )
    }

    override suspend fun GetCompanyPictureByIdAsync(
        companyPictureId: Int
    ): Result<CompanyPictureUpdateModel?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPictureByIdAsync",
            query = "companyPictureId=$companyPictureId"
        )
    }

    override suspend fun GetCompanyPictureByIdExtendedAsync(
        companyPictureId: Int
    ): Result<CompanyPictureDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "GetCompanyPictureByIdExtendedAsync",
            query = "companyPictureId=$companyPictureId"
        )
    }

    override suspend fun InsertAsync(
        model: CompanyPictureInsertModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "InsertAsync",
            data = model
        )
    }

    override suspend fun UpdateAsync(
        model: CompanyPictureUpdateModel
    ): Result<Unit> {
        return apiClient.PostAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "UpdateAsync",
            data = model
        )
    }

    override suspend fun DeleteAsync(
        companyPictureId: Int
    ): Result<Unit> {
        return apiClient.DeleteAsync(
            baseUrl = ApiRoutes.RESOURCE_BASE_URL,
            method = "DeleteAsync",
            query = "companyPictureId=$companyPictureId"
        )
    }
}