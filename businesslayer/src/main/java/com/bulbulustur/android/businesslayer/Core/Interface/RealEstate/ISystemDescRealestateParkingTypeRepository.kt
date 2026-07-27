package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateParkingTypeDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateParkingTypeInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateParkingTypeUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateParkingTypeRepository {
    suspend fun GetSystemDescRealestateParkingTypesAsync(): Result<List<SystemDescRealestateParkingTypeDTO>>
    suspend fun GetSystemDescRealestateParkingTypeByIdAsync(systemDescRealestateParkingTypeId: Int): Result<SystemDescRealestateParkingTypeUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateParkingTypeInsertModel): Result<SystemDescRealestateParkingTypeInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateParkingTypeUpdateModel): Result<SystemDescRealestateParkingTypeUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateParkingTypeId: Int): Result<Unit>
}