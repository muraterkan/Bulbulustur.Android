package com.bulbulustur.android.businesslayer.Core.Repository

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Interface.ICompanyRepository
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Network.ApiClient
import com.bulbulustur.android.businesslayer.Core.Network.ApiRoutes
import com.bulbulustur.android.businesslayer.Core.Util.Result

class CompanyRepository(private val apiClient: ApiClient = ApiClient) : ICompanyRepository {

    override suspend fun GetAccountCompanyAsync(languageId: Int, memberId: Int): Result<CompanyDTO?> {
        return apiClient.GetAsync(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Company/GetAccountCompanyAsync",
            query = "languageId=$languageId&memberId=$memberId"
        )
    }

    override suspend fun UpdateAccountCompanyAsync(memberId: Int, updateModel: CompanyUpdateModel): Result<Any?> {
        return apiClient.PutAsync<CompanyUpdateModel, Any?>(
            baseUrl = ApiRoutes.COMMERCE_SUPPORT_BASE_URL,
            method = "Company/UpdateAccountCompanyAsync",
            query = "memberId=$memberId",
            data = updateModel
        )
    }
}