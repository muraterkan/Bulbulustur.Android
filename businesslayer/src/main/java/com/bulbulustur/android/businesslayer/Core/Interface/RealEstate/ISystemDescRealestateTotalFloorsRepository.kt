package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateTotalFloorsDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateTotalFloorsInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateTotalFloorsUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateTotalFloorsRepository {
    suspend fun GetSystemDescRealestateTotalFloorssAsync(): Result<List<SystemDescRealestateTotalFloorsDTO>>
    suspend fun GetSystemDescRealestateTotalFloorsByIdAsync(systemDescRealestateTotalFloorsId: Int): Result<SystemDescRealestateTotalFloorsUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateTotalFloorsInsertModel): Result<SystemDescRealestateTotalFloorsInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateTotalFloorsUpdateModel): Result<SystemDescRealestateTotalFloorsUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateTotalFloorsId: Int): Result<Unit>
}