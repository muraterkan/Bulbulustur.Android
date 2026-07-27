package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateMortgageStatusDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateMortgageStatusInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateMortgageStatusUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateMortgageStatusRepository {
    suspend fun GetSystemDescRealestateMortgageStatussAsync(): Result<List<SystemDescRealestateMortgageStatusDTO>>
    suspend fun GetSystemDescRealestateMortgageStatusByIdAsync(systemDescRealestateMortgageStatusId: Int): Result<SystemDescRealestateMortgageStatusUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateMortgageStatusInsertModel): Result<SystemDescRealestateMortgageStatusInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateMortgageStatusUpdateModel): Result<SystemDescRealestateMortgageStatusUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateMortgageStatusId: Int): Result<Unit>
}