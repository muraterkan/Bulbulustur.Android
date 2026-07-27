package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateBuildingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateBuildingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateBuildingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateBuildingTypeRepository {
    suspend fun GetSystemDescRealestateBuildingTypesAsync(): Result<List<SystemDescRealestateBuildingTypeDTO>>
    suspend fun GetSystemDescRealestateBuildingTypeByIdAsync(systemDescRealestateBuildingTypeId: Int): Result<SystemDescRealestateBuildingTypeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateBuildingTypeInsertModel): Result<SystemDescRealestateBuildingTypeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateBuildingTypeUpdateModel): Result<SystemDescRealestateBuildingTypeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateBuildingTypeId: Int): Result<Unit>
}