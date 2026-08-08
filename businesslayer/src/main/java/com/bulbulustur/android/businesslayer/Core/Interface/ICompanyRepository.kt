package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import com.bulbulustur.android.businesslayer.Core.Util.PaginatedList

interface ICompanyRepository {
        suspend fun GetCompaniesAsync(languageId: Int, page: Int = 1, pageSize: Int = 20): Result<PaginatedList<CompanyDTO>>
suspend fun GetCompanyAsync(languageId: Int, companyId: Int): Result<CompanyDTO?>
    suspend fun GetCompanyByMemberAsync(languageId: Int, memberId: Int): Result<CompanyDTO?>
    suspend fun UpdateCompanyAsync(memberId: Int, updateModel: CompanyUpdateModel): Result<Any?>
    suspend fun GetAccountCompanyAsync(languageId: Int, memberId: Int): Result<CompanyDTO?> = GetCompanyByMemberAsync(languageId = languageId, memberId = memberId)
    suspend fun UpdateAccountCompanyAsync(memberId: Int, updateModel: CompanyUpdateModel): Result<Any?> = UpdateCompanyAsync(memberId = memberId, updateModel = updateModel)
}
