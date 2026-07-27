package com.bulbulustur.android.businesslayer.Core.Interface.RealEstate

import com.bulbulustur.android.businesslayer.Core.DTO.RealEstate.SystemDescRealestateEnergyCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.RealEstate.SystemDescRealestateEnergyCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.RealEstate.SystemDescRealestateEnergyCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface ISystemDescRealestateEnergyCertificateRepository {
    suspend fun GetSystemDescRealestateEnergyCertificatesAsync(): Result<List<SystemDescRealestateEnergyCertificateDTO>>
    suspend fun GetSystemDescRealestateEnergyCertificateByIdAsync(systemDescRealestateEnergyCertificateId: Int): Result<SystemDescRealestateEnergyCertificateUpdateModel>
    suspend fun InsertAsync(model: SystemDescRealestateEnergyCertificateInsertModel): Result<SystemDescRealestateEnergyCertificateInsertModel>
    suspend fun UpdateAsync(model: SystemDescRealestateEnergyCertificateUpdateModel): Result<SystemDescRealestateEnergyCertificateUpdateModel>
    suspend fun DeleteAsync(systemDescRealestateEnergyCertificateId: Int): Result<Unit>
}