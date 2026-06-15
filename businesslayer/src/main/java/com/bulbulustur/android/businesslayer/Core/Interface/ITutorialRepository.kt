package com.bulbulustur.android.businesslayer.Core.Interface import com.bulbulustur.android.businesslayer.Core.Util.Result

import com.bulbulustur.android.businesslayer.Core.DTO.TutorialDTO
import com.bulbulustur.android.businesslayer.Core.Model.UpdateModels.TutorialUpdateModel

interface ITutorialRepository {

    suspend fun GetTutorialListAsync(): Result<List<TutorialDTO>>

    suspend fun GetTutorialByIdAsync(
        tutorialId: Int
    ): Result<TutorialUpdateModel?>

    suspend fun GetTutorialByIdExtendedAsync(
        tutorialId: Int
    ): Result<TutorialDTO?>
}
