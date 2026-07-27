package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateSellerTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateSellerTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateSellerTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateSellerTypeRepository {
    suspend fun GetSystemDescRealestateSellerTypesAsync(): Result<List<SystemDescRealestateSellerTypeDTO>>
    suspend fun GetSystemDescRealestateSellerTypeByIdAsync(systemDescRealestateSellerTypeId: Int): Result<SystemDescRealestateSellerTypeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateSellerTypeInsertModel): Result<SystemDescRealestateSellerTypeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateSellerTypeUpdateModel): Result<SystemDescRealestateSellerTypeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateSellerTypeId: Int): Result<Unit>
}