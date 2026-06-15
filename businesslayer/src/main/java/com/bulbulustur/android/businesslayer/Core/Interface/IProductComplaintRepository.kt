package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.ProductComplaintDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.ProductComplaintUpdateModel

interface IProductComplaintRepository {

    suspend fun GetProductComplaintListAsync(): Result<List<ProductComplaintDTO>>

    suspend fun GetProductComplaintByIdAsync(
        complaintId: Int
    ): Result<ProductComplaintUpdateModel?>

    suspend fun GetProductComplaintByIdExtendedAsync(
        complaintId: Int
    ): Result<ProductComplaintDTO?>
}
