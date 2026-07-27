package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateHeatingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateHeatingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateHeatingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateHeatingTypeRepository {
    suspend fun GetSystemDescRealestateHeatingTypesAsync(): Result<List<SystemDescRealestateHeatingTypeDTO>>
    suspend fun GetSystemDescRealestateHeatingTypeByIdAsync(systemDescRealestateHeatingTypeId: Int): Result<SystemDescRealestateHeatingTypeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateHeatingTypeInsertModel): Result<SystemDescRealestateHeatingTypeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateHeatingTypeUpdateModel): Result<SystemDescRealestateHeatingTypeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateHeatingTypeId: Int): Result<Unit>
}