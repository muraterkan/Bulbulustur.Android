package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateKitchenTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateKitchenTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateKitchenTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateKitchenTypeRepository {
    suspend fun GetSystemDescRealestateKitchenTypesAsync(): Result<List<SystemDescRealestateKitchenTypeDTO>>
    suspend fun GetSystemDescRealestateKitchenTypeByIdAsync(systemDescRealestateKitchenTypeId: Int): Result<SystemDescRealestateKitchenTypeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateKitchenTypeInsertModel): Result<SystemDescRealestateKitchenTypeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateKitchenTypeUpdateModel): Result<SystemDescRealestateKitchenTypeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateKitchenTypeId: Int): Result<Unit>
}