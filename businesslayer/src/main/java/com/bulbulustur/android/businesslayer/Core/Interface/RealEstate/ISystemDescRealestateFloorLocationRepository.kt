package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateFloorLocationDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateFloorLocationInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateFloorLocationUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateFloorLocationRepository {
    suspend fun GetSystemDescRealestateFloorLocationsAsync(): Result<List<SystemDescRealestateFloorLocationDTO>>
    suspend fun GetSystemDescRealestateFloorLocationByIdAsync(systemDescRealestateFloorLocationId: Int): Result<SystemDescRealestateFloorLocationUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateFloorLocationInsertModel): Result<SystemDescRealestateFloorLocationInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateFloorLocationUpdateModel): Result<SystemDescRealestateFloorLocationUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateFloorLocationId: Int): Result<Unit>
}