package com.bulbulustur.android.Application.Localization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object BBLocalization {

    private var CurrentState by mutableStateOf(
        LocalizationState(
            IsInitialized = true
        )
    )

    val Current: LocalizationState
        get() = CurrentState

    @Composable
    internal fun Bind(
        state: LocalizationState
    ) {
        SideEffect {
            if (CurrentState != state) {
                CurrentState = state
            }
        }
    }
}