package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.SystemDescYesNoDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.SystemDescYesNoInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.SystemDescYesNoUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ISystemDescYesNoRepository {

    @GET("api/SystemDescYesNo/GetSystemDescYesNoListAsync")
    suspend fun GetSystemDescYesNoListAsync():
            Result<List<SystemDescYesNoDTO>>

    @GET("api/SystemDescYesNo/GetSystemDescYesNoByIdAsync")
    suspend fun GetSystemDescYesNoByIdAsync(
        @Query("systemDescYesNoId")
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoUpdateModel?>

    @GET("api/SystemDescYesNo/GetSystemDescYesNoByIdExtendedAsync")
    suspend fun GetSystemDescYesNoByIdExtendedAsync(
        @Query("systemDescYesNoId")
        systemDescYesNoId: Int
    ): Result<SystemDescYesNoDTO?>

    @POST("api/SystemDescYesNo/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: SystemDescYesNoInsertModel
    ): Result<Unit>

    @POST("api/SystemDescYesNo/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: SystemDescYesNoUpdateModel
    ): Result<Unit>

    @POST("api/SystemDescYesNo/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("systemDescYesNoId")
        systemDescYesNoId: Int
    ): Result<Unit>
}
