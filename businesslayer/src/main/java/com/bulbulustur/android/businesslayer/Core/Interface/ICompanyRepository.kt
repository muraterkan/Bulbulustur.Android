package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ICompanyRepository {
    suspend fun GetAccountCompanyAsync(languageId: Int, memberId: Int): Result<CompanyDTO?>
    suspend fun UpdateAccountCompanyAsync(memberId: Int, updateModel: CompanyUpdateModel): Result<Any?>
}