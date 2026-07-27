package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBuildingAgeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBuildingAgeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBuildingAgeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateBuildingAgeRepository {
    suspend fun GetSystemDescRealestateBuildingAgesAsync(): Result<List<SystemDescRealestateBuildingAgeDTO>>
    suspend fun GetSystemDescRealestateBuildingAgeByIdAsync(systemDescRealestateBuildingAgeId: Int): Result<SystemDescRealestateBuildingAgeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateBuildingAgeInsertModel): Result<SystemDescRealestateBuildingAgeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateBuildingAgeUpdateModel): Result<SystemDescRealestateBuildingAgeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateBuildingAgeId: Int): Result<Unit>
}