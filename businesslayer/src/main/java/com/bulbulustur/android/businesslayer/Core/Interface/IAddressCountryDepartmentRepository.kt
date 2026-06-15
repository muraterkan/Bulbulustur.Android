package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.AddressCountryDepartmentDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.AddressCountryDepartmentInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.AddressCountryDepartmentUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IAddressCountryDepartmentRepository {

    @GET("api/AddressCountryDepartment/GetAddressCountryDepartmentListAsync")
    suspend fun GetAddressCountryDepartmentListAsync():
            Result<List<AddressCountryDepartmentDTO>>

    @GET("api/AddressCountryDepartment/GetAddressCountryDepartmentByIdAsync")
    suspend fun GetAddressCountryDepartmentByIdAsync(
        @Query("addressCountryDepartmentId")
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentUpdateModel?>

    @GET("api/AddressCountryDepartment/GetAddressCountryDepartmentByIdExtendedAsync")
    suspend fun GetAddressCountryDepartmentByIdExtendedAsync(
        @Query("addressCountryDepartmentId")
        addressCountryDepartmentId: Int
    ): Result<AddressCountryDepartmentDTO?>

    @POST("api/AddressCountryDepartment/InsertAsync")
    suspend fun InsertAsync(
        @Body
        model: AddressCountryDepartmentInsertModel
    ): Result<Unit>

    @POST("api/AddressCountryDepartment/UpdateAsync")
    suspend fun UpdateAsync(
        @Body
        model: AddressCountryDepartmentUpdateModel
    ): Result<Unit>

    @POST("api/AddressCountryDepartment/DeleteAsync")
    suspend fun DeleteAsync(
        @Query("addressCountryDepartmentId")
        addressCountryDepartmentId: Int
    ): Result<Unit>
}
