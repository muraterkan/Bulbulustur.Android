package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.WholesaleProductCertificateDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.WholesaleProductCertificateUpdateModel

interface IWholesaleProductCertificateRepository {

    suspend fun GetWholesaleProductCertificateListAsync(): Result<List<WholesaleProductCertificateDTO>>

    suspend fun GetWholesaleProductCertificateByIdAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateUpdateModel?>

    suspend fun GetWholesaleProductCertificateByIdExtendedAsync(
        wholesaleProductCertificateId: Int
    ): Result<WholesaleProductCertificateDTO?>
}
