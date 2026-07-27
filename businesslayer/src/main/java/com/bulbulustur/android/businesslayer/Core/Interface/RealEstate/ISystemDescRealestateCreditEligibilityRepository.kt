package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateCreditEligibilityDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateCreditEligibilityInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateCreditEligibilityUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateCreditEligibilityRepository {
    suspend fun GetSystemDescRealestateCreditEligibilitysAsync(): Result<List<SystemDescRealestateCreditEligibilityDTO>>
    suspend fun GetSystemDescRealestateCreditEligibilityByIdAsync(systemDescRealestateCreditEligibilityId: Int): Result<SystemDescRealestateCreditEligibilityUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateCreditEligibilityInsertModel): Result<SystemDescRealestateCreditEligibilityInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateCreditEligibilityUpdateModel): Result<SystemDescRealestateCreditEligibilityUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateCreditEligibilityId: Int): Result<Unit>
}