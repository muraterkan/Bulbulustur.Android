package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.WholesaleProductCertificateInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCertificateUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IWholesaleProductCertificateRepository {

    @GET("api/WholesaleProductCertificate/GetWholesaleProductCertificateListAsync")
    suspend fun GetWholesaleProductCertificateListAsync():
            Result<List<WholesaleProductCertificateDTO>>

    @GET("api/WholesaleProductCertificate/GetWholesaleProductCertificateByIdAsync")
    suspend fun GetWholesaleProductCertificateByIdAsync(
        @Query("wholesaleProductCertificateId")
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateUpdateModel?>

    @GET("api/WholesaleProductCertificate/GetWholesaleProductCertificateByIdExtendedAsync")
    suspend fun GetWholesaleProductCertificateByIdExtendedAsync(
        @Query("wholesaleProductCertificateId")
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateDTO?>

    @POST("api/WholesaleProductCertificate/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: WholesaleProductCertificateInsertModel
    ): Result<Unit>

    @POST("api/WholesaleProductCertificate/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: WholesaleProductCertificateUpdateModel
    ): Result<Unit>

    @POST("api/WholesaleProductCertificate/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("wholesaleProductCertificateId")
        wholesaleProductCertificateId: Int
    ): Result<Unit>
}
