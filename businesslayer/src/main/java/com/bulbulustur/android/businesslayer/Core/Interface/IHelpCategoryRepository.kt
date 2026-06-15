package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.HelpCategoryDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.HelpCategoryInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.HelpCategoryUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IHelpCategoryRepository {

    @GET("api/HelpCategory/GetHelpCategoryListAsync")
    suspend fun GetHelpCategoryListAsync():
            Result<List<HelpCategoryDTO>>

    @GET("api/HelpCategory/GetHelpCategoryByIdAsync")
    suspend fun GetHelpCategoryByIdAsync(
        @Query("helpCategoryId")
        helpCategoryId: Int
    ): Result<HelpCategoryUpdateModel?>

    @GET("api/HelpCategory/GetHelpCategoryByIdExtendedAsync")
    suspend fun GetHelpCategoryByIdExtendedAsync(
        @Query("helpCategoryId")
        helpCategoryId: Int
    ): Result<HelpCategoryDTO?>

    @POST("api/HelpCategory/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: HelpCategoryInsertModel
    ): Result<Unit>

    @POST("api/HelpCategory/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: HelpCategoryUpdateModel
    ): Result<Unit>

    @POST("api/HelpCategory/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("helpCategoryId")
        helpCategoryId: Int
    ): Result<Unit>
}
