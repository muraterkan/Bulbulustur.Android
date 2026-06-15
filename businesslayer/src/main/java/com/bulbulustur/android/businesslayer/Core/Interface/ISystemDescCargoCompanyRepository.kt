package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescCargoCompanyDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescCargoCompanyInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescCargoCompanyUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescCargoCompanyRepository {

    @GET("api/SystemDescCargoCompany/GetSystemDescCargoCompanyListAsync")
    suspend fun GetSystemDescCargoCompanyListAsync():
            Result<List<SystemDescCargoCompanyDTO>>

    @GET("api/SystemDescCargoCompany/GetSystemDescCargoCompanyByIdAsync")
    suspend fun GetSystemDescCargoCompanyByIdAsync(
        @Query("systemDescCargoCompanyId")
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyUpdateModel?>

    @GET("api/SystemDescCargoCompany/GetSystemDescCargoCompanyByIdExtendedAsync")
    suspend fun GetSystemDescCargoCompanyByIdExtendedAsync(
        @Query("systemDescCargoCompanyId")
        systemDescCargoCompanyId: Int
    ): Result<SystemDescCargoCompanyDTO?>

    @POST("api/SystemDescCargoCompany/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescCargoCompanyInsertModel
    ): Result<Unit>

    @POST("api/SystemDescCargoCompany/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescCargoCompanyUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescCargoCompany/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescCargoCompanyId")
        systemDescCargoCompanyId: Int
    ): Result<Unit>
}
