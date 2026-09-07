package com.bulbulustur.android.Application.Areas.b2b.Views.Filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bulbulustur.android.Application.Areas.b2b.Controllers.WholesaleFilterControllerState
import com.bulbulustur.android.Application.wwwroot.DesignTokens.BBSpacing

@Composable
fun WholesaleProductFilterScreen(
    state: WholesaleFilterControllerState = WholesaleFilterControllerState(),
    onBackClick: () -> Unit = {},
    onClearClick: () -> Unit = {},
    onApplyClick: () -> Unit = {},
    onFilterGroupClick: (String) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(BBSpacing.PageHorizontal)
    ) {
        Text(
            text = "Filtrele",
            style = MaterialTheme.typography.headlineSmall
        )

        state.Groups.forEach { group ->
            Text(
                text = group.Title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(
                    top = BBSpacing.Space4
                )
            )
        }
    }
}
