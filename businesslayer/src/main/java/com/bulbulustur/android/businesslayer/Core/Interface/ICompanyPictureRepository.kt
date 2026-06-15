package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.CompanyPictureDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.CompanyPictureInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.CompanyPictureUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ICompanyPictureRepository {

    @GET("api/CompanyPicture/GetCompanyPictureListAsync")
    suspend fun GetCompanyPictureListAsync():
            Result<List<CompanyPictureDTO>>

    @GET("api/CompanyPicture/GetCompanyPictureByIdAsync")
    suspend fun GetCompanyPictureByIdAsync(
        @Query("companyPictureId")
        companyPictureId: Int
    ): Result<CompanyPictureUpdateModel?>

    @GET("api/CompanyPicture/GetCompanyPictureByIdExtendedAsync")
    suspend fun GetCompanyPictureByIdExtendedAsync(
        @Query("companyPictureId")
        companyPictureId: Int
    ): Result<CompanyPictureDTO?>

    @POST("api/CompanyPicture/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: CompanyPictureInsertModel
    ): Result<Unit>

    @POST("api/CompanyPicture/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: CompanyPictureUpdateModel
    ): Result<Unit>

    @POST("api/CompanyPicture/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("companyPictureId")
        companyPictureId: Int
    ): Result<Unit>
}
