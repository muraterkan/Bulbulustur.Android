package com.bulbulustur.android.businesslayer.Core.Interface

import com.bulbulustur.android.businesslayer.Core.DTO.FaqDTO
import com.bulbulustur.android.businesslayer.Core.Model.InsertModels.FaqInsertModel
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.FaqUpdateModel
import com.bulbulustur.android.businesslayer.Core.Util.Result

interface IFaqRepository {

    suspend fun GetFaqs(languageId: Int, faqSectionId: Int, count: Int = 100): Result<List<FaqDTO>>

    suspend fun GetFaqById(languageId: Int, helpId: Int): Result<FaqUpdateModel?>

    suspend fun GetFaqByIdExtended(languageId: Int, helpId: Int): Result<FaqDTO?>

    suspend fun Insert(model: FaqInsertModel): Result<Unit>

    suspend fun Update(model: FaqUpdateModel): Result<Unit>

    suspend fun Delete(helpId: Int): Result<Unit>
}