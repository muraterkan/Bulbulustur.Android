package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCategorySupplierDTO
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IWholesaleProductCategorySupplierRepository {
    suspend fun GetWholesaleProductCategorySuppliers(languageId: Int, productCategoryId: Int, count: Int = 2): Result<List<WholesaleProductCategorySupplierDTO>>
}
